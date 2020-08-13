package ua.axiom.persistance.misc.annotations;

import ua.axiom.persistance.misc.representation.PersistingDepersistingStrategyProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PersistingStrategy {
    PersistingDepersistingStrategyProvider strategy() default PersistingDepersistingStrategyProvider.ORDINAL;
}
