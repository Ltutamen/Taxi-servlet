package ua.axiom.core;

import ua.axiom.core.context.ApplicationContext;
import ua.axiom.core.context.ObjectFactory;

/**
 * App class, singleton that controlls object creation, holds created objects, entry point
 */
public class App {
    private static App app;

    private ApplicationContext context;
    private ApplicationConfiguration configuration;
    private ObjectFactory factory;

    public static App getApp() {
        if(app == null) {
            synchronized (App.class) {
                if(app == null) {
                    app = new App();
                }
            }
        }

        return app;
    }

    public <T> T getObject(Class<T> tClass) {
        if(context.hasImplementation(tClass)) {
            return context.getImplementation(tClass);
        }

        Class<? extends T> implClass = getImplType(tClass);

        T obj = null;
        try {
            obj = factory.createObject(implClass);
        } catch (Throwable tr) {
            System.err.println(tr.getMessage());
            throw new RuntimeException(tr.getCause());
        }

        context.addImplementation(tClass, obj);
        return obj;
    }

    private <T> Class<? extends T> getImplType(Class<T> forClass) {
        if(forClass.isInterface()) {
            return configuration.getImplementationForInterface(forClass).iterator().next();
        }
        return forClass;
    }
}
