package ua.axiom.core;

import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.IdGenerationQuery;
import ua.axiom.persistance.repository.impl.*;
import ua.axiom.security.PasswordEncoder;
import ua.axiom.security.PasswordEncoderProvider;
import ua.axiom.service.*;
import ua.axiom.service.buisness.CarService;
import ua.axiom.service.buisness.OrderService;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Context {
    private static final Context singleton = new Context();
    private static boolean isSealed = false;

    private final Map<Class<?>, Object> elements = new HashMap<>();

    static {
        try {
            Context.put(new SimpleDBConnectionProvider());
            ClientRepository clientRepository = new ClientRepository();
            AdminRepository adminRepository = new AdminRepository();
            DriverRepository driverRepository = new DriverRepository();

            Context.put(new IdGenerationQuery(Context.get(SimpleDBConnectionProvider.class)));

            Context.put(new ClientRepository());
            Context.put(new AdminRepository());
            Context.put(new DriverRepository());
            Context.put(new OrderRepository());
            Context.put(new CarRepository());

            Context.put(new MultiTableRepository(Arrays.asList(clientRepository, adminRepository, driverRepository)));

            Context.put(new LocalisationService());
            Context.put(new PasswordEncoderProvider());
            Context.put(new GuiService());
            Context.put(new CarService());
            Context.put(new OrderService());
            Context.put(new LoginService());
            Context.put(new InputValidationService());
            Context.put(new CommandProviderService());


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
        }
    }

    public static synchronized void put(Object object) {
        if(isSealed) {
            throw new RuntimeException("Context is sealed!");
        }
        if(singleton.elements.containsKey(object.getClass())) {
            throw new IllegalArgumentException("Class " + object.getClass() + " already exists");
        }

        singleton.elements.put(object.getClass(), object);
    }

    public static <T> T get(Class<T> type) {
        if (singleton.elements.get(type) == null) {
            throw new IllegalArgumentException("No such class in context: " + type);
        }

        return (T)singleton.elements.get(type);
    }

    public static synchronized void seal() {
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
            return paramClass != boolean.class;
        }

        return true;
    }
}

