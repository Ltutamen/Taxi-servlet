package ua.axiom.core.annotations.processors;

import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.context.ApplicationContext;

public interface AnnotationProcessorI {
    void process(Object object, ApplicationContext context, ApplicationConfiguration configuration);

}
