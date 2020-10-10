package ua.axiom.core.context;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * Creates and configures objects
 */
@Component
public class ObjectFactory {
    private static Logger logger = Logger.getLogger(ObjectFactory.class.getName());

    private ObjectConfigurator configurator;

    public ObjectFactory(ApplicationContext context, ApplicationConfiguration configuration) {
        configurator = new ObjectConfigurator(context, configuration);
    }

    public <T> T createObject(Class<T> implClass) throws Throwable{

        logger.log(Level.TRACE, "create object of type: " + implClass);

        T t = create(implClass);

        configurator.configure(t);

        return t;
    }

    private <T> T create(Class<T> implClass) throws Throwable {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
