package ua.axiom.core.annotations.processors;

import ua.axiom.core.App;
import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.context.ApplicationContext;

import java.util.Arrays;

@AnnotationProcessor(Autowired.class)
public class AutowiredProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContext context, ApplicationConfiguration configuration) {

        Arrays.stream(object
                    .getClass()
                    .getDeclaredFields())
                .filter(field -> field.getAnnotation(Autowired.class) != null)
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        field.set(object, App.getApp().getObject(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e.getCause());
                    }
                });
    }
}
