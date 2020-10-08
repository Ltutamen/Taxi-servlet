package ua.axiom.core;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

/**
 * Provides information about annotated classes and about implementation classes
 */
public interface ApplicationConfiguration {
    <T> Collection<Class<? extends T>> getImplementationForInterface(Class<T> tClass);

    Set<Class<?>> getClassesAnnotatedWith(Class<? extends Annotation> tClass) ;
}
