package ua.axiom.core.context;

//  What this class does?
/*
public class ContextProcessor {
    private static final Set<Class<? extends Annotation>> HANDLED_ANNOTATIONS = new HashSet<>(Arrays.asList(Autowired.class));

    private final Map<Class<? extends Annotation>, AnnotationProcessorI> annotationToProcessorMap = new HashMap<>();

    public ContextProcessor(Set<Class<?>> annotationProcessorClasses) {
        for (Class<?> cclass : annotationProcessorClasses) {
            if (!AnnotationProcessorI.class.isAssignableFrom(cclass)) {
                throw new IllegalStateException("Class <" + cclass.getName() + "> must inherit from <" + AnnotationProcessorI.class.getName() + "> interface");
            }

            Class<AnnotationProcessorI> annotationProcessorImplClass = (Class<AnnotationProcessorI>) cclass;

            try {
                Constructor<? extends AnnotationProcessorI> processorConstructor = annotationProcessorImplClass.getConstructor();
                AnnotationProcessorI processor = processorConstructor.newInstance();

                Annotation annotation = annotationProcessorImplClass.getAnnotation(AnnotationProcessor.class);

                annotationToProcessorMap.put(annotation.annotationType(), processor);

            } catch (NoSuchMethodException | IllegalAccessException e) {
                throw new IllegalStateException("No default public constructor for annotation handler implementation <" + annotationProcessorImplClass.getName() + ">");
            } catch (InstantiationException | InvocationTargetException e) {
                throw new IllegalStateException(e.getCause());
            }
        }
    }
}
*/
