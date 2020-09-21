package ua.axiom.core.context;

import ua.axiom.core.ObjectFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private static ApplicationContext instance;

    private ApplicationContextAnnotatedClassesProvider annotationsContext;
    private ObjectFactory factory;
    private Map<Class<?>, Object> impCache = new ConcurrentHashMap<>();

    public static void init() {
        //  yes, I had do write MY OWN singleton!
        if(instance == null) {
            synchronized (ApplicationContext.class) {
                if (instance == null) {
                    instance = new ApplicationContext();

                }
            }
        }
    }

    public static ApplicationContext getInstance() {
        return instance;
    }

    public ApplicationContext() {
        this.annotationsContext = new HardcodedAnnotatedClassesProvider();
        this.factory = new ObjectFactory(annotationsContext);
    }

    public <T> T getObject(Class<T> type) {
        System.out.println(this.getClass() + "getObject, type " + type);
        if (impCache.containsKey(type)) {
            return (T) impCache.get(type);
        }

        Class<? extends T> implClass = getImplType(type);

        if (type.isInterface()) {
            throw new RuntimeException("Wiring interface types not implemented yet");
        }

        T t = factory.createObject(implClass);

        impCache.put(type, t);


        return t;
    }

    private <T> Class<? extends T> getImplType(Class<T> intOrImplType) {
        return (Class<? extends T>) ApplicationConfiguration.INTERFACE_TO_IMPLEMENTATION_MAP.getOrDefault(intOrImplType, intOrImplType);
    }

}
