package ua.axiom.core.context;

import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.core.AnnotationProcessingOrder;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.annotations.processors.AnnotationProcessorI;
import ua.axiom.core.exceptions.AnnotationProcessorConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class ObjectConfigurator {
    private static final int DEFAULT_ANNOTATION_PROCESSING_ORDER = 1;
    //  compares Annotation Processor Implementation based on given order from AnnotationProcessingOrder annotation, or using default order
    private static final Comparator<AnnotationProcessorI> ANNOTATION_PROCESSING_ORDER_COMPARATOR = (entry1, entry2) -> {
        //  function to extract order from AnnotationProcessorImpl
        ToIntFunction<AnnotationProcessorI> orderFromAnnotationExtractionFunc = (entry) -> {
            Optional<AnnotationProcessingOrder> optionalAnnotationProcessingOrder = Optional.ofNullable(
                    entry.getClass().getAnnotation(AnnotationProcessor.class).value().getAnnotation(AnnotationProcessingOrder.class));

            return optionalAnnotationProcessingOrder.map(AnnotationProcessingOrder::value).orElse(DEFAULT_ANNOTATION_PROCESSING_ORDER);
        };

        return orderFromAnnotationExtractionFunc.applyAsInt(entry1) - orderFromAnnotationExtractionFunc.applyAsInt(entry2);
    };

    private final ApplicationContext applicationContext;

    private final ApplicationConfiguration applicationConfiguration;

    private final List<AnnotationProcessorI> configurators;

    public ObjectConfigurator(ApplicationContext applicationContext, ApplicationConfiguration configuration) {
        this.applicationContext = applicationContext;
        this.applicationConfiguration = configuration;

        configurators = configuration
                .getClassesAnnotatedWith(AnnotationProcessor.class)
                .stream()
                .map(clazz -> {
                    try {
                        Constructor<AnnotationProcessorI> constructor = (Constructor<AnnotationProcessorI>) clazz.getConstructor();
                        return constructor.newInstance();
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new AnnotationProcessorConstructorException(clazz, e.getCause().toString(), e.getMessage());
                    }
                })
                .sorted(ANNOTATION_PROCESSING_ORDER_COMPARATOR)
                .collect(Collectors.toList());
    }

    public <T> void configure(T object) {

        configurators
                .forEach(configurator -> configurator.process(object, applicationContext, applicationConfiguration));

    }
}
