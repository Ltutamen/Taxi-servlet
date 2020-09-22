package ua.axiom.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Only one @CommandMappingService annotated class must exist
 * Class, annotated with @CommandMappingService annotation, must be of type CommandProviderI
 * @RequestMapping annotated classes added as an mapping to this annotated class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandMappingService {
}
