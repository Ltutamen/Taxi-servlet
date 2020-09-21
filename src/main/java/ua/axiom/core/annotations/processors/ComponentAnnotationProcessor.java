package ua.axiom.core.annotations.processors;

import ua.axiom.core.annotations.AnnotationProcessor;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.context.ApplicationContextAnnotatedClassesProvider;

/**
 * Component annotated classes are those classes, tha
 */
@AnnotationProcessor(Component.class)
public class ComponentAnnotationProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContextAnnotatedClassesProvider context) {

    }
}
