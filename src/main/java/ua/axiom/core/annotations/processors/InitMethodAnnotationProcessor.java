package ua.axiom.core.annotations.processors;

import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.core.context.ApplicationContextAnnotatedClassesProvider;

import javax.management.openmbean.InvalidKeyException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@AnnotationProcessor(InitMethod.class)
public class InitMethodAnnotationProcessor implements AnnotationProcessorI {
    @Override
    public void process(Object object, ApplicationContextAnnotatedClassesProvider context) {

        Arrays.stream(object.getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(InitMethod.class) != null)
                .peek(method -> method.setAccessible(true))
                .peek(method -> {
                    if(method.getParameterCount() != 0) {
                        throw new IllegalStateException("Init method <" + method.getName() + "> in class <" + object.getClass() + "> should have no parametres");
                    }
                })
                .forEach(method -> {
                    try {
                        method.invoke(object);
                    } catch (InvalidKeyException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException("Exception calling init method <" + method.getName() + "> for class <" + object.getClass() + "> with cause: <\n" + e.getCause() + "\n>");
                    }
                });
    }
}
