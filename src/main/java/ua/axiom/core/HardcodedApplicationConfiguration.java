package ua.axiom.core;

import ua.axiom.controller.commands.*;
import ua.axiom.controller.commands.viewable.AdminPageCommand;
import ua.axiom.controller.commands.viewable.ClientPageCommand;
import ua.axiom.controller.commands.viewable.ErrorCommand;
import ua.axiom.controller.commands.viewable.NewOrderCommand;
import ua.axiom.controller.commands.viewable.driverpage.DriverPageCommand;
import ua.axiom.controller.commands.viewable.mainpage.LoggedMainPageCommand;
import ua.axiom.controller.commands.viewable.mainpage.MainPageCommand;
import ua.axiom.core.annotations.CommandMappingService;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.core.annotations.core.AnnotationProcessor;
import ua.axiom.core.annotations.processors.*;
import ua.axiom.model.actors.Admin;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.Driver;
import ua.axiom.persistance.dao.*;
import ua.axiom.persistance.ormbased.SessionFactoryProvider;
import ua.axiom.persistance.ormbased.repository.impl.*;
import ua.axiom.service.*;
import ua.axiom.service.buisness.CarService;
import ua.axiom.service.buisness.ClientPageService;
import ua.axiom.service.buisness.OrderService;
import ua.axiom.util.GenericSetBuilder;
import ua.axiom.util.MapBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * This class exists only because org.reflections does not work, and does not return classes, annotated with specific annotation
 */
public class HardcodedApplicationConfiguration implements ApplicationConfiguration {
    @Override
    public <T> Class<? extends T> getImplType(Class<T> someClass) {
        if(!someClass.isInterface() && !Modifier.isAbstract(someClass.getModifiers())) {
            //  if class is not interface AND not abstract, return class
            return someClass;
        }

        Class<? extends T> implementation = (Class<? extends T>) INTERFACE_TO_IMPLEMENTATION_MAP.get(someClass);

        if(implementation == null) {
            throw new RuntimeException(getClass() + " error: implementation not configured for type: " + someClass);
        }

        return implementation;
    }

    public <T> Collection<Class<? extends T>> getSubTypes(Class<T> forClass) {
        Collection<Class<?>> subTypes = SUB_TYPES_MAP.get(forClass);

        if(subTypes == null) {
            throw new RuntimeException(getClass() + " error: subTypes not configured for type: " + forClass);
        }

        Collection<Class<? extends T>> subClasses = new GenericSetBuilder<Class<? extends T>>()
                .addAllWithCast(subTypes)
                .build();

        return subClasses;
    }



    @Override
    public Set<Class<?>> getClassesAnnotatedWith(Class<? extends Annotation> tClass) {
        return ANNOTATION_TO_ANNOTATED_CLASSES_MAP.get(tClass);
    }

    private Set<Class<?>> ANNOTATION_PROCESSOR_ANNOTATED_CLASSES = Collections.unmodifiableSet(new GenericSetBuilder<Class<? extends AnnotationProcessorI>>()
            .addElement(AutowiredCollectionProcessor.class)
            .addElement(AutowiredProcessor.class)
            .addElement(BeanProcessor.class)
            .addElement(CommandMappingServiceAnnotationService.class)
            .addElement(ComponentAnnotationProcessor.class)
            .addElement(InitMethodAnnotationProcessor.class)
            .addElement(MainServletAnnotationProcessor.class)
            .addElement(RequestMappingAnnotationProcessor.class)
            .build()
    );

    private Set<Class<?>> COMPONENT_ANNOTATED_OBJECTS_CLASSES = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(

                    OrderService.class,
                    ClientPageService.class,
                    SessionContextService.class,
                    LoggedMainPageCommand.class,
                    GuiService.class,
                    LocalisationService.class,
                    DriverSessionContextService.class,
                    CarService.class,
                    SessionFactoryProvider.class,
                    DriverSessionContextService.class,
                    OrderRepositoryORM.class,
                    DriverRepositoryORM.class,
                    ClientRepositoryORM.class,
                    AdminRepositoryORM.class
            )));

    private Set<Class<?>> REQUEST_MAPPING_ANNOTATED_CLASSES = Collections.unmodifiableSet(new GenericSetBuilder<Class<?>>()
            .addElement(ClientPageCommand.class)
            .addElement(DriverPageCommand.class)
            .addElement(AdminPageCommand.class)
            .addElement(NewOrderCommand.class)
            .addElement(NewOrderPostCommand.class)
            .addElement(PostLoginCommand.class)
            .addElement(LoginPageCommand.class)
            .addElement(LogoutCommand.class)
            .addElement(ErrorCommand.class)
            .addElement(MainPageCommand.class)
            .addElement(DriverPageTakeOrder.class)
            .addElement(DriverPageOrderConfirmation.class)
            .addElement(ClientPageOrderConfirmation.class)
            .addElement(ClientPageCancelOrder.class)
            .build());

    private Set<Class<?>> COMMAND_MAPPING_SERVICE_ANNOTATED_CLASSES = Collections.singleton(CommandToRequestMappingService.class);


    private Map<Class<? extends Annotation>, Set<Class<?>>> ANNOTATION_TO_ANNOTATED_CLASSES_MAP = new MapBuilder<Class<? extends Annotation>, Set<Class<?>>>()
            .addPair(Component.class, COMPONENT_ANNOTATED_OBJECTS_CLASSES)
            .addPair(CommandMappingService.class, COMMAND_MAPPING_SERVICE_ANNOTATED_CLASSES)
            .addPair(RequestMapping.class, REQUEST_MAPPING_ANNOTATED_CLASSES)
            .addPair(AnnotationProcessor.class, ANNOTATION_PROCESSOR_ANNOTATED_CLASSES)
            .build();

    private Map<Class<?>, Class<?>> INTERFACE_TO_IMPLEMENTATION_MAP = new MapBuilder<Class<?>, Class<?>>()
            .addPair(AdminDao.class, AdminRepositoryORM.class)
            .addPair(DriverDao.class, DriverRepositoryORM.class)
            .addPair(OrderDao.class, OrderRepositoryORM.class)
            .addPair(ClientDao.class, ClientRepositoryORM.class)
            .addPair(UserDao.class, UserRepositoryORM.class)
            .build();

    private Map<Class<?>, Collection<Class<?>>> SUB_TYPES_MAP = new MapBuilder<Class<?>, Collection<Class<?>>>()
            .build();

}
