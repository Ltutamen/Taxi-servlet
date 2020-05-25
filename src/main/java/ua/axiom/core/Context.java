package ua.axiom.core;

import ua.axiom.model.actors.User;
import ua.axiom.persistance.repository.*;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Context {
    private static final Context singleton = new Context();
    private static boolean isSealed = false;

    private final Map<Class<?>, Object> elements = new HashMap<>();

    static {
        try {
            ClientRepository clientRepository = new ClientRepository();
            AdminRepository adminRepository = new AdminRepository();

            Context.put(clientRepository);
            Context.put(adminRepository);

            Context.put(new MultiTableRepository<>(Arrays.asList(clientRepository, adminRepository)));
            Context.put(new LocalisationService());
            Context.put(new GuiService());

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    public static void put(Object object) {
        if(isSealed) {
            throw new RuntimeException("Context is sealed!");
        }
        if(singleton.elements.containsKey(object.getClass())) {
            throw new IllegalArgumentException("Class " + object.getClass() + " already exists");
        }

        singleton.elements.put(object.getClass(), object);
    }

    public static <T> T get(Class<T> type, Object... constructorParams) {
        if (singleton.elements.get(type) == null) {
            Object o = null;
            conLoop:
            for (Constructor con : type.getConstructors()) {
                if (constructorParams.length == con.getParameterCount()) {
                    for (int i = 0; i < constructorParams.length; ++i) {
                        if (wrongTypeCheck(constructorParams[i], con.getParameterTypes()[i])) {
                            continue conLoop;
                        }
                    }
                    try {
                        o = con.newInstance(constructorParams);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

            if (o == null) {
                throw new IllegalArgumentException("no matching constructor to call on class "
                        + type.toString()
                        + " with arguments: "
                        + Arrays.toString(constructorParams));
            }

            singleton.elements.put(type, o);
        }

        return (T)singleton.elements.get(type);
    }

    public void seal() {
        Context.isSealed = true;
    }


    private static boolean wrongTypeCheck (Object conObj, Class paramClass) {
        //  standart non-wrapped classes
        if(conObj.getClass() == paramClass) {
            return false;
        }
        //  Wrapper classes
        else if(conObj instanceof Number) {
            if(conObj instanceof Short && paramClass.equals(short.class))
                return false;
            if(conObj instanceof Integer && paramClass.equals(int.class))
                return false;
            if(conObj instanceof Long && paramClass.equals(long.class))
                return false;
            if(conObj instanceof Float && paramClass.equals(float.class))
                return false;
            if(conObj instanceof Double && paramClass.equals(double.class))
                return false;
            else {
                System.err.println("add wrapper class handling to the Singleton class :" + conObj);
            }

            return true;
        }
        else if(conObj instanceof Boolean) {
            if(paramClass == boolean.class) {
                return false;
            }
        }

        return true;
    }
}

