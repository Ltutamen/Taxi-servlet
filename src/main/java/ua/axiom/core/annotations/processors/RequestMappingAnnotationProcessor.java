package ua.axiom.core.annotations.processors;

import ua.axiom.core.context.ApplicationContextAnnotatedClassesProvider;

public class RequestMappingAnnotationProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContextAnnotatedClassesProvider context) {
        System.out.println("request mapping processor on class " + object.getClass());
    }
}
