package ua.axiom.core.annotations.processors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.AutowiredCollection;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.context.ApplicationContext;

import java.util.Arrays;

@AnnotationProcessor(AutowiredCollection.class)
public class AutowiredCollectionProcessor implements AnnotationProcessorI {
    private final Logger logger = Logger.getLogger(AutowiredCollectionProcessor.class);

    @Override
    public void process(Object object, ApplicationContext context, ApplicationConfiguration configuration) {
        //  todo NOW!!
        Arrays
                .stream(object.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(AutowiredCollection.class) != null)
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    Class<?> objTypeToFill = field.getAnnotation(AutowiredCollection.class).value();
                    logger.log(Level.DEBUG, "processing annotation from class: " + object + " for field: " + field + ";");
                    /*try {
                        Collection<?> collectionToFill = (Collection<?>)field.get(object);
                        Collection<? extends Class> classesToFill =  context.getApplicationContext()
                        ApplicationContext.getInstance().getObject(field.getType());

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("cause: " + e.getCause() + " msg: " + e.getMessage());
                    }*/

                });
    }
}
