package ua.axiom.core.annotations.processors;

import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.annotations.CommandMappingService;
import ua.axiom.core.context.ApplicationContextAnnotatedClassesProvider;

@AnnotationProcessor(CommandMappingService.class)
public class MainServletAnnotationProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContextAnnotatedClassesProvider context) {
        System.out.println("main servlet annotation processor for class " + object.getClass());
    }
}
