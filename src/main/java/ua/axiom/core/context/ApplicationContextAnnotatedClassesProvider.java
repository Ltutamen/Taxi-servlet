package ua.axiom.core.context;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface ApplicationContextAnnotatedClassesProvider {
    Set<Class<?>> getClassesAnnotatedWith(Class<? extends Annotation> annotation);
}
