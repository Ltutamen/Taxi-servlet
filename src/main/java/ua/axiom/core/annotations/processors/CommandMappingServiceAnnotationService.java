package ua.axiom.core.annotations.processors;

import ua.axiom.controller.Command;
import ua.axiom.core.App;
import ua.axiom.core.ApplicationConfiguration;
import ua.axiom.core.annotations.CommandMappingService;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.context.ApplicationContext;
import ua.axiom.service.CommandToRequestMappingService;
import ua.axiom.util.GenericCollectionsUtil;

import java.util.HashSet;
import java.util.Set;

@AnnotationProcessor(CommandMappingService.class)
public class CommandMappingServiceAnnotationService implements AnnotationProcessorI {
    @Override
    //  todo move out
    public void process(Object object, ApplicationContext context, ApplicationConfiguration configuration) {
        if(object.getClass().getAnnotation(CommandMappingService.class) == null) {
            return;
        }

        CommandToRequestMappingService mappingService = (CommandToRequestMappingService)object;

        Set<Class<? extends Command<?>>> commandClasses = GenericCollectionsUtil.toCollection(HashSet::new, configuration.getClassesAnnotatedWith(RequestMapping.class));

        for (Class<? extends Command<?>> cClass : commandClasses) {
            try {
                Command<?> command = App.getApp().getObject(cClass);
                mappingService.addCommand(getURLPathFromAnnotation(cClass), command);
            } catch (Throwable t) {
                t.printStackTrace();
                throw new RuntimeException(t.getCause());
            }


        }
    }

    private static String getURLPathFromAnnotation(Class<? extends Command<?>> commandClass) {
        try {
            RequestMapping mappingAnnotation = commandClass.getAnnotation(RequestMapping.class);
            return mappingAnnotation.value();

        } catch (NullPointerException npe) {
            throw new RuntimeException("Command class <" + commandClass +"> does not have @RequestMapping annotation");
        }
    }
}
