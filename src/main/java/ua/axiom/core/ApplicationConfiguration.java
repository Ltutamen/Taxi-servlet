package ua.axiom.core;

import ua.axiom.controller.commands.*;
import ua.axiom.controller.commands.viewable.AdminPageCommand;
import ua.axiom.controller.commands.viewable.ClientPageCommand;
import ua.axiom.controller.commands.viewable.ErrorCommand;
import ua.axiom.controller.commands.viewable.NewOrderCommand;
import ua.axiom.controller.commands.viewable.driverpage.DriverPageCommand;
import ua.axiom.controller.commands.viewable.mainpage.LoggedMainPageCommand;
import ua.axiom.controller.commands.viewable.mainpage.MainPageCommand;
import ua.axiom.core.annotations.*;
import ua.axiom.core.annotations.processors.*;
import ua.axiom.model.actors.factories.AdminFactory;
import ua.axiom.model.actors.factories.ClientFactory;
import ua.axiom.model.actors.factories.DriverFactory;
import ua.axiom.model.actors.factories.OrderFactory;
import ua.axiom.persistance.dao.AdminDao;
import ua.axiom.persistance.dao.ClientDao;
import ua.axiom.persistance.dao.DriverDao;
import ua.axiom.persistance.dao.OrderDao;
import ua.axiom.persistance.jdbcbased.database.DBConnectionProvider;
import ua.axiom.persistance.jdbcbased.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.jdbcbased.query.IdGenerationQuery;
import ua.axiom.persistance.jdbcbased.repository.MultiTableRepository;
import ua.axiom.persistance.jdbcbased.repository.impl.*;
import ua.axiom.service.*;
import ua.axiom.service.buisness.CarService;
import ua.axiom.service.buisness.ClientPageService;
import ua.axiom.service.buisness.OrderService;
import ua.axiom.util.GenericSetBuilder;
import ua.axiom.util.MapBuilder;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * This class exists only because org.reflections does not work, and does not return classes, annotated with specific annotation
 */
public interface ApplicationConfiguration {

    Map<Class<? extends Annotation>, Class<? extends AnnotationProcessorI>> ANNOTATION_TO_ANNOTATION_PROCESSOR_MAP =
            new MapBuilder<Class<? extends Annotation>, Class<? extends AnnotationProcessorI>>()
                    .addPair(Bean.class, BeanProcessor.class)
                    .addPair(Autowired.class, AutowiredProcessor.class)
                    .addPair(RequestMapping.class, RequestMappingAnnotationProcessor.class)
                    .addPair(CommandMappingService.class, CommandMappingServiceAnnotationService.class)
                    .addPair(Component.class, ComponentAnnotationProcessor.class)
                    .addPair(InitMethod.class, InitMethodAnnotationProcessor.class)
                    .build();


    HashSet<Class<?>> COMPONENT_ANNOTATED_OBJECTS_CLASSES = new HashSet<>(
            Arrays.asList(
                    DriverRepository.class,
                    DriverFactory.class,
                    ClientRepository.class,
                    ClientFactory.class,
                    OrderRepository.class,
                    OrderFactory.class,
                    AdminFactory.class,
                    AdminRepository.class,

                    OrderService.class,
                    ClientPageService.class,
                    SessionContextService.class,
                    LoggedMainPageCommand.class,
                    GuiService.class,
                    LocalisationService.class,
                    DriverSessionContextService.class,
                    CarService.class,
                    IdGenerationQuery.class,
                    MultiTableRepository.class,
                    ClientFactory.class,
                    OrderFactory.class


            ));

    Set<Class<?>> REQUEST_MAPPING_ANNOTATED_CLASSES = new GenericSetBuilder<Class<?>>()
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
            .build();

    Set<Class<?>> COMMAND_MAPPING_SERVICE_ANNOTATED_CLASSES = Collections.singleton(CommandToRequestMappingService.class);


    Map<Class<? extends Annotation>, Set<Class<?>>> ANNOTATION_TO_ANNOTATED_CLASSES_MAP = new MapBuilder<Class<? extends Annotation>, Set<Class<?>>>()
            .addPair(Component.class, COMPONENT_ANNOTATED_OBJECTS_CLASSES)
            .addPair(CommandMappingService.class, COMMAND_MAPPING_SERVICE_ANNOTATED_CLASSES)
            .addPair(RequestMapping.class, REQUEST_MAPPING_ANNOTATED_CLASSES)
            .build();

    Map<Class<?>, Class<?>> INTERFACE_TO_IMPLEMENTATION_MAP = new MapBuilder<Class<?>, Class<?>>()
            .addPair(DBConnectionProvider.class, SimpleDBConnectionProvider.class)
            .addPair(AdminDao.class, AdminRepository.class)
            .addPair(DriverDao.class, DriverRepository.class)
            .addPair(OrderDao.class, OrderRepository.class)
            .addPair(ClientDao.class, ClientRepository.class)
            .build();

}
