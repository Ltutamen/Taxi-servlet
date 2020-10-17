package ua.axiom.controller.commands.viewable.driverpage;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.Autowired;
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

public class DriverWithOrderPageCommand extends Command<Driver> {
    @Autowired
    private LocalisationService localisationService;
    @Autowired
    private GuiService guiService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CarService carService;

    @Override
    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "forward:/appPages/driverpage/withOrder.jsp";
    }

    @Override
    protected void userSpecificDataFill(Map<String, Object> model, Driver driver) {
        guiService.userSpecificModelPopulation(model, driver);

        model.put("balance", driver.getMoney());
        model.put("car", carService.getCarById(driver.getCarId()));

        Order order = orderService.getOrderForDriverAndStatus(driver.getId(), Order.Status.PENDING).iterator().next();
        model.put("order", order);
    }

    @Override
    protected void nonUserSpecificDataFill(Map<String, Object> model, UserLocale userLocale) {
        localisationService.setLocalisedMessages(
                model,
                userLocale.toJavaLocale(),
                "sentence.current-order-description-msg",
                "word.from",
                "word.to",
                "sentence.sentence-confirm-msg"
        );
    }
}
