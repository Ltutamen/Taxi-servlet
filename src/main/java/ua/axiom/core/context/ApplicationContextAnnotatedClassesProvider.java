package ua.axiom.core.context;

import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.annotations.processors.AnnotationProcessorI;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface ApplicationContextAnnotatedClassesProvider {
    Set<Class<?>> getClassesAnnotatedWith(Class<? extends Annotation> annotation);

    Set<Class<? extends Annotation>> getProcessedAnnotations();

    ApplicationContext getApplicationContext();

    Class<? extends AnnotationProcessorI> getAnnotationProcessor(Class<? extends Annotation> forClass);
}
