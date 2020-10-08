package ua.axiom.core.annotations.processors;

import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.AutowiredCollection;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.context.ApplicationContext;

@AnnotationProcessor(AutowiredCollection.class)
public class AutowiredCollectionProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContext context, ApplicationConfiguration configuration) {
        //  todo NOW!!
        /*Arrays
                .stream(object.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(AutowiredCollection.class) != null)
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    Class<?> objTypeToFill = field.getAnnotation(AutowiredCollection.class).value();
                    try {
                        Collection<?> collectionToFill = (Collection<?>)field.get(object);
                        Collection<? extends Class> classesToFill =  context.getApplicationContext()
                        ApplicationContext.getInstance().getObject(field.getType());

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("cause: " + e.getCause() + " msg: " + e.getMessage());
                    }

                });*/
    }
}
