package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.Order;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;
import ua.axiom.service.buisness.CarService;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DriverNoOrderPageCommand extends Command<Driver> {
    private LocalisationService localisationService;
    private GuiService guiService;
    private OrderService orderService;
    private CarService carService;

    {
        localisationService = Context.get(LocalisationService.class);
        guiService = Context.get(GuiService.class);
        orderService = Context.get(OrderService.class);
        carService = Context.get(CarService.class);
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
        model.put("orders", orderService.getSuitableOrders(user));

        guiService.userSpecificModelPopulation(model, user);

        model.put("balance", user.getMoney());

        model.put("car", carService.getCarById(user.getCarId()));
    }
}
