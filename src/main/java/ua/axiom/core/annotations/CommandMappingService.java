package ua.axiom.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Only one @CommandProvider annotated class must exist
// * Class, annotated with @MainServlet annotation, must be of type CommandProviderI
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandMappingService {
}
