package ua.axiom.core.annotations.processors;

import ua.axiom.core.context.ApplicationContextAnnotatedClassesProvider;

public interface AnnotationProcessorI {
    void process(Object object, ApplicationContextAnnotatedClassesProvider context);

}
