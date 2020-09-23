package ua.axiom.persistance.jdbcbased.misc.annotations;

import ua.axiom.persistance.jdbcbased.misc.representation.PersistingDepersistingStrategyProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to provide metadata for persisting and de-persisting Enums
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PersistingStrategy {
    PersistingDepersistingStrategyProvider strategy() default PersistingDepersistingStrategyProvider.ORDINAL;
}
