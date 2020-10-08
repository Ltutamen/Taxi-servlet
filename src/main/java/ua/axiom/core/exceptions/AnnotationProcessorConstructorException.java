package ua.axiom.core.exceptions;

/**
 * Thrown when class, annotated with AnnotationProcessor annotation does not have constructor without params
 */
public class AnnotationProcessorConstructorException extends RuntimeException {
    private final Class<?> annotated;
    private final String cause;
    private final String message;

    public AnnotationProcessorConstructorException(Class<?> annotated, String cause, String message) {
        this.annotated = annotated;
        this.cause = cause;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Exception initializing annotation processor <" + annotated + ">\n" +
                " cause:  <" + cause + ">\n" +
                " message: <" + message + ">" ;
    }
}
