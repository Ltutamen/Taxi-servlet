package ua.axiom.core.context;

import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.core.AnnotationProcessingOrder;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.annotations.processors.AnnotationProcessorI;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.ToIntFunction;

/**
 * Creates and configures objects
 */
@Component
public class ObjectFactory {
    //  AppContext holds already created instances;

    private static final int DEFAULT_ANNOTATION_PROCESSING_ORDER = 1;


    private ObjectConfigurator configurator;

    public ObjectFactory() {
    }

    public <T> T createObject(Class<T> implClass) {

        T t = create(implClass);

        configurator.configure(t);

        return t;
    }

    private <T> T create(Class<T> implClass) {
        try {
            return implClass.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e.getCause() + "for class <" + implClass + ">");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }
}
