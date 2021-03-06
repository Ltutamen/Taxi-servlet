package ua.axiom.core.annotations.processors;

import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.annotations.Bean;
import ua.axiom.core.context.ApplicationContext;
import ua.axiom.core.context.ApplicationContextAnnotatedClassesProvider;

@AnnotationProcessor(Bean.class)
public class BeanProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContext context, ApplicationConfiguration configuration) {

    }
}
