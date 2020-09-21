package ua.axiom.core.annotations.processors;

import ua.axiom.core.annotations.AnnotationProcessor;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.context.ApplicationContext;
import ua.axiom.core.context.ApplicationContextAnnotatedClassesProvider;

import java.util.Arrays;

@AnnotationProcessor(Autowired.class)
public class AutowiredProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContextAnnotatedClassesProvider context) {
        System.out.println("autowired proc for " + object.getClass() + " total + " + Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(Autowired.class) != null)
                .count());

        Arrays.stream(object
                    .getClass()
                    .getDeclaredFields())
                .filter(field -> field.getAnnotation(Autowired.class) != null)
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        field.set(object, ApplicationContext.getInstance().getObject(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e.getCause());
                    }
                });
    }

}
