package ua.axiom.core;

import org.reflections.Reflections;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Bean;
import ua.axiom.core.annotations.handlers.AnnotationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class Application {
    private final String classpath;
    private Set<AnnotationHandler> handlers = new HashSet<>();

    public Application(String classpath) {
        this.classpath = classpath;
    }

    //  todo @Autowire annotation
    //  todo @Bean annotation
    public void run() {
        Reflections reflection = new Reflections(classpath);

        /*Set<Object> beans = reflection
                .getFieldsAnnotatedWith(Bean.class)
                .stream().forEach(field -> {if (! field.getType().equals(Method.class)) {throw new RuntimeException("@Bean works on methods only! ");} });
        reflection.getFieldsAnnotatedWith()*/



    }
}
