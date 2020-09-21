package ua.axiom.core.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationProcessor {
    Class<? extends Annotation> value();
}
