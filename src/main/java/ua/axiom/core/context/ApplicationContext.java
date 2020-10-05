package ua.axiom.core.context;

import org.apache.log4j.Logger;
import ua.axiom.core.ApplicationConfiguration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private static ApplicationContext instance;
    private static Logger logger = Logger.getLogger(ApplicationContext.class);

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
        logger.info("<" + getClass()  + "> constructor");
        this.annotationsContext = new HardcodedAnnotatedClassesProvider();
        this.factory = new ObjectFactory(annotationsContext);
    }

    public <T> T getObject(Class<T> type) {
        logger.info("<" + type + "> getObject request");
        if (impCache.containsKey(type)) {
            logger.info("<" + type + "> getObject request from cache");
            return (T) impCache.get(type);
        }

        Class<? extends T> implClass = getImplType(type);
        logger.info("<" + implClass + "> is an implementation class for class <" + type + ">");

        //  already implemented
/*        if (type.isInterface()) {
            throw new RuntimeException("Wiring interface types not implemented yet");
        }*/

        T t = null;
        try {
            t = factory.createObject(implClass);
        } catch (Throwable tr) {
            System.err.println(tr.getMessage());
            logger.error(tr.getCause() + "for class <" + implClass + ">");
            throw new RuntimeException(tr.getCause());
        }

        impCache.put(type, t);


        return t;
    }

    private <T> Class<? extends T> getImplType(Class<T> intOrImplType) {
        return (Class<? extends T>) ApplicationConfiguration.INTERFACE_TO_IMPLEMENTATION_MAP.getOrDefault(intOrImplType, intOrImplType);
    }

}
