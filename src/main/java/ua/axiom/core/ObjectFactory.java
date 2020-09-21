package ua.axiom.core;

import org.omg.SendingContext.RunTime;
import ua.axiom.core.annotations.AnnotationProcessor;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.processors.AnnotationProcessorI;
import ua.axiom.core.context.AnnotationProcessingOrder;
import ua.axiom.core.context.ApplicationConfiguration;
import ua.axiom.core.context.ApplicationContextAnnotatedClassesProvider;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

@Component
public class ObjectFactory {
    private static final Class<? extends Annotation> ANNOTATION_PROCESSOR_ANNOTATION = AnnotationProcessor.class;
    private static final int DEFAULT_ANNOTATION_PROCESSING_ORDER = 1;

    private static final Comparator<Map.Entry<Class<? extends Annotation>, AnnotationProcessorI>> ANNOTATION_PROCESSING_ORDER_COMPARATOR = (entry1, entry2) -> {
        ToIntFunction<Map.Entry<Class<? extends Annotation>, AnnotationProcessorI>> orderFromAnnotationExtractionFunc = (entry) -> {
            AnnotationProcessingOrder orderAnnotation = entry.getKey().getAnnotation(AnnotationProcessingOrder.class);
            return orderAnnotation == null ? DEFAULT_ANNOTATION_PROCESSING_ORDER : orderAnnotation.value();
        };

        return orderFromAnnotationExtractionFunc.applyAsInt(entry1) - orderFromAnnotationExtractionFunc.applyAsInt(entry2);
    };

    private final ApplicationContextAnnotatedClassesProvider context;

    private final Map<Class<? extends Annotation>, AnnotationProcessorI> configurators;

    //  todo replace AppContext with interface
    public ObjectFactory(ApplicationContextAnnotatedClassesProvider context) {
        this.context = context;
        this.configurators = new HashMap<>();

        for(Map.Entry<Class<? extends Annotation>, Class<? extends AnnotationProcessorI>> configurationEntry : getAnnotationToAnnotationProcessorsClasses().entrySet()) {
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

        /*this.configurators = getAnnotationToAnnotationProcessorsClasses()
                .entrySet()
                .stream()
                .map(entry -> {
                    try {
                        AnnotationProcessorI implementation =
                                entry.getValue()
                                .getConstructor()
                                .newInstance();

                        return new AbstractMap.SimpleEntry<Class<? extends Annotation>, AnnotationProcessorI>(entry.getKey(), implementation);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException("Annotation processor must have public constructor with no args");
                    }
                })
                .collect(
                        Collectors.toMap(e -> e.getKey(), e-> e.getValue()),
                        (conflictedVal1, conflictedVal2) -> {throw new IllegalStateException("conflict between " + conflictedVal1 + " and " + conflictedVal2);},
                        () -> new SortedMap<>((entry1, entry2) -> {
                    ToIntFunction<MapEntry<Class<? extends Annotation>, AnnotationProcessorI>> processOrderFromAnnotatedClassExtractFunction = MapEntry::getKey::
                            getClass()::getAnnotation(AnnotationProcessingOrder.class);
                })));*/
    }

    public <T> T createObject(Class<T> implClass) {

        T t = create(implClass);

        configure(t, implClass);

        return t;
    }

    private <T> T create(Class<T> implClass) {
        try {
            return implClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No public constructor for class <" + implClass + ">");
        } catch (InstantiationException ie) {
            throw new RuntimeException("Instantiation exception for class <" + implClass + ">");
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    private <T> void configure(T t, Class<T> tClass) {

        configurators
                .entrySet()
                .stream()
                .sorted(ANNOTATION_PROCESSING_ORDER_COMPARATOR)
/*                .peek(e -> {
                    AnnotationProcessingOrder annotation = e.getKey().getAnnotation(AnnotationProcessingOrder.class);
                    int order = annotation == null ? DEFAULT_ANNOTATION_PROCESSING_ORDER : annotation.value();
                    System.err.println(order + ":" + e.getKey());
                })*/
                .forEach((entry) -> entry.getValue().process(t, context));

/*        Set<Annotation> annotations = new HashSet<>(Arrays.asList(tClass.getAnnotations()));*/
/*        Set<Class<? extends Annotation>> annotationClasses = annotations
                .stream()
                .map(Annotation::annotationType)
                .collect(Collectors.toSet());*/
/*        configurators
                .entrySet()
                .stream()
                .filter(entry -> annotationClasses.contains(entry.getKey()))
                .peek(e -> System.out.println(":" + e))
                .map(Map.Entry::getValue)
                .forEach(
                        annotationProcessorI ->
                        {
                            annotationProcessorI.process(t, context);
                            System.out.println("config for <" + t.getClass().getSimpleName() + ">, config <" + annotationProcessorI.getClass().getSimpleName() + ">");
                        });*/
    }

    private Map<Class<? extends Annotation>, Class<? extends AnnotationProcessorI>> getAnnotationToAnnotationProcessorsClasses() {
        //  Doesnt really work for no apparent reason
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
