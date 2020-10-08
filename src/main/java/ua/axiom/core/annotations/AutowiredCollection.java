package ua.axiom.core.annotations;

import ua.axiom.core.annotations.core.AnnotationProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutowiredCollection {
    Class<?> value();
}
