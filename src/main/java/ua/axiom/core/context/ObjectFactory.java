package ua.axiom.core.context;

import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.core.AnnotationProcessingOrder;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.annotations.processors.AnnotationProcessorI;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.ToIntFunction;

@Component
public class ObjectFactory {
    private static final Class<? extends Annotation> ANNOTATION_PROCESSOR_ANNOTATION = AnnotationProcessor.class;
    private static final int DEFAULT_ANNOTATION_PROCESSING_ORDER = 1;

    private static final Comparator<Map.Entry<Class<? extends Annotation>, AnnotationProcessorI>> ANNOTATION_PROCESSING_ORDER_COMPARATOR = (entry1, entry2) -> {
        ToIntFunction<Map.Entry<Class<? extends Annotation>, AnnotationProcessorI>> orderFromAnnotationExtractionFunc = (entry) -> {
            Optional<AnnotationProcessingOrder> orderAnnotationOptional = Optional.ofNullable(entry.getKey().getAnnotation(AnnotationProcessingOrder.class));
            return orderAnnotationOptional.map(AnnotationProcessingOrder::value).orElse(DEFAULT_ANNOTATION_PROCESSING_ORDER);
        };

        return orderFromAnnotationExtractionFunc.applyAsInt(entry1) - orderFromAnnotationExtractionFunc.applyAsInt(entry2);
    };

    private final ApplicationContextAnnotatedClassesProvider context;

    private final Map<Class<? extends Annotation>, AnnotationProcessorI> configurators;


    public ObjectFactory(ApplicationContextAnnotatedClassesProvider context) {
        this.context = context;
        this.configurators = new HashMap<>();

        for (Map.Entry<Class<? extends Annotation>, Class<? extends AnnotationProcessorI>> configurationEntry : getAnnotationToAnnotationProcessorsClasses().entrySet()) {
            AnnotationProcessorI implementation = null;

            try {
                implementation =
                        configurationEntry.getValue()
                                .getConstructor()
                                .newInstance();

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException("Annotation processor must have public constructor with no args");
            }

            AnnotationProcessorI leftover = configurators.put(configurationEntry.getKey(), implementation);
            assert leftover == null;
        }
    }

    public <T> T createObject(Class<T> implClass) {

        T t = create(implClass);

        configure(t, implClass);

        return t;
    }

    private <T> T create(Class<T> implClass) {
        try {
            return implClass.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e.getCause() + "for class <" + implClass + ">");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    private <T> void configure(T t, Class<T> tClass) {

        configurators
                .entrySet()
                .stream()
                .sorted(ANNOTATION_PROCESSING_ORDER_COMPARATOR)
                .forEach((entry) -> entry.getValue().process(t, context));

    }

    private Map<Class<? extends Annotation>, Class<? extends AnnotationProcessorI>> getAnnotationToAnnotationProcessorsClasses() {
        //  Doesnt work for no apparent reason
        /*return  context
                .getReflections()
                .getTypesAnnotatedWith(ANNOTATION_PROCESSOR_ANNOTATION)
                .stream()
                .map(type -> {
                    try {
                        return type
                                .getConstructor()
                                .newInstance();
                    } catch (NoSuchMethodException | IllegalAccessException e) {
                        throw new RuntimeException("Annotation processors should have public default constructor");
                    } catch (InstantiationException | InvocationTargetException e) {
                        throw new RuntimeException(e.getCause());
                    }
                })
                .map(ap -> (AnnotationProcessorInt)ap)
                .collect(Collectors.toSet());*/
        return ApplicationConfiguration.ANNOTATION_TO_ANNOTATION_PROCESSOR_MAP;
    }

}
