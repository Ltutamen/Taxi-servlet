package ua.axiom.core.annotations;

import ua.axiom.core.annotations.core.AnnotationProcessingOrder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Methods, annotated with @InitMethod, will be called after constructor call
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@AnnotationProcessingOrder(2)
public @interface InitMethod {
}
