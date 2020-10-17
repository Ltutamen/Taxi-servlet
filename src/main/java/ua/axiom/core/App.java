package ua.axiom.core;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import ua.axiom.core.context.ApplicationContext;
import ua.axiom.core.context.ObjectFactory;

import java.lang.reflect.Modifier;
import org.apache.logging.log4j.Logger;


/**
 * App class, singleton that controlls object creation, holds created objects, entry point
 */
public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());
    private static App app;

    private ApplicationContext context;
    private ApplicationConfiguration configuration;
    private ObjectFactory factory;

    public static App getApp() {
        if(app == null) {
            synchronized (App.class) {
                if(app == null) {
                    try {
                        app = new App();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                        throw new RuntimeException(throwable.getCause());
                    }
                }
            }
        }

        return app;
    }

    public App() {
        this.configuration = new HardcodedApplicationConfiguration();
        this.context = new ApplicationContext(configuration);
        this.factory = new ObjectFactory(context, configuration);
    }

    public <T> T getObject(Class<T> tClass) {
        try {


            logger.log(Level.DEBUG, " getObject called for: " + tClass.getName());
            if (context.hasImplementation(tClass)) {
                logger.log(Level.DEBUG, "getObject, return implementation from context: " + context.getImplementation(tClass));
                return context.getImplementation(tClass);
            }

            Class<? extends T> implClass = getImplType(tClass);
            logger.log(Level.DEBUG, "impl type for: " + tClass + " is: " + implClass);

            T obj = null;
            try {
                obj = factory.createObject(implClass);
            } catch (Throwable tr) {
                logger.log(Level.ERROR, "exception creating class: " + implClass + "\n,exception: " + tr.getCause() + "\n, message: " + tr.getMessage());
                throw new RuntimeException(tr.getMessage());
            }

            logger.log(Level.DEBUG, "Object: " + tClass + " created");
            context.addImplementation(tClass, obj);
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable.getCause());
        }
    }

    private <T> Class<? extends T> getImplType(Class<T> forClass) {
        if(forClass.isInterface() || Modifier.isAbstract(forClass.getModifiers())) {
            return configuration.getImplType(forClass);
        }

        return forClass;
    }
}
