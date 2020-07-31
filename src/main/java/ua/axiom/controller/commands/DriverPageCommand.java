package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.repository.impl.CarRepository;
import ua.axiom.persistance.repository.impl.OrderRepository;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class DriverPageCommand extends Command<Driver> {
    private LocalisationService localisationService;
    private GuiService guiService;
    private OrderRepository orderRepository;
    private CarRepository carRepository;

    {
        localisationService = Context.get(LocalisationService.class);
        guiService = Context.get(GuiService.class);
        orderRepository = Context.get(OrderRepository.class);
        carRepository = Context.get(CarRepository.class);
    }

    @Override
    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/appPages/driverpage/noOrder.jsp";
    }

    @Override
    protected void nonUserSpecificDataFill(Map<String, Object> model, UserLocale userLocale) {
        localisationService.setLocalisedMessages(
                model,
                userLocale.toJavaLocale(),
                "sentence.take-order",
                "sentence.accessible-orders",
                "word.withdraw",
                "sentence.your-cars",
                "word.balance",
                "word.withdraw",
                "sentence.change-car",
                "word.class",
                "word.car-model",
                "word.from",
                "word.to"
        );
    }

    @Override
    protected void userSpecificDataFill(Map<String, Object> model, Driver user) {
        model.put(
                "orders",
                orderRepository.findByFields(
                        Arrays.asList("c_class", "status"),
                        Arrays.asList(
                                carRepository.findOne(user.getCarId()).iterator().next().getaClass().toString(),
                                Order.Status.PENDING.toString())));

        guiService.userSpecificModelPopulation(model, user);
        model.put("balance", user.getMoney());
        model.put("car", carRepository.findOne(user.getCarId()).iterator().next().getaClass().toString());
    }
}
