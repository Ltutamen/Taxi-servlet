package ua.axiom.core.context;

import ua.axiom.core.ApplicationConfiguration;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class HardcodedAnnotatedClassesProvider implements ApplicationContextAnnotatedClassesProvider {
    private Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClassesCache;

    public HardcodedAnnotatedClassesProvider() {
        this.annotatedClassesCache =  ApplicationConfiguration.ANNOTATION_TO_ANNOTATED_CLASSES_MAP;
    }

    @Override
    public Set<Class<?>> getClassesAnnotatedWith(Class<? extends Annotation> annotation) {
        return annotatedClassesCache.getOrDefault(annotation, Collections.emptySet());
    }

}
