package ua.axiom.core.annotations.handlers;

import java.lang.annotation.Annotation;

public abstract class AnnotationHandler {
    public final Annotation annotation;

    public AnnotationHandler(Annotation annotation) {
        this.annotation = annotation;
    }
}
