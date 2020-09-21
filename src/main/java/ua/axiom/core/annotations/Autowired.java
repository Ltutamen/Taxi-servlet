package ua.axiom.core.annotations;

import ua.axiom.core.context.AnnotationProcessingOrder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
//  autowire first, then - init method call
@AnnotationProcessingOrder(0)
public @interface Autowired {
}
