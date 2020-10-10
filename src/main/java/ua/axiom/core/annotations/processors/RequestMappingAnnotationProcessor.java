package ua.axiom.core.annotations.processors;

import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.context.ApplicationContext;

@AnnotationProcessor(RequestMapping.class)
public class RequestMappingAnnotationProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContext context, ApplicationConfiguration configuration) {

    }
}
