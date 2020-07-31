package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.Order;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class NewOrderCommand extends Command<Client> {
    private LocalisationService localisationService;
    private GuiService guiService;

    {
        guiService = Context.get(GuiService.class);
        localisationService = Context.get(LocalisationService.class);
    }

    @Override
    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getRequestURI());
        return getView();
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        return super.executePost(request, response);
    }

    @Override
    protected void nonUserSpecificDataFill(Map<String, Object> model, UserLocale userLocale) {

        localisationService.setLocalisedMessages(
                model,
                userLocale.toJavaLocale(),
                "word.hello",
                "word.menu",
                "sentence.new-order",
                "sentence.your-balance",
                "sentence.cancel-order",
                "sentence.promocodes",
                "sentence.replenish-balance",
                "sentence.sentence-confirm-msg",
                "sentence.new-order-request-msg",
                "sentence.delete-account",
                "info.username",
                "word.from",
                "word.to",
                "word.class",
                "word.fee",
                "word.page",
                "word.cancel",
                "word.balance",
                "sentence.your-orders",
                "sentence.order-history"
        );

        model.put("car_classes", Car.Class.values());
    }

    @Override
    protected void userSpecificDataFill(Map<String, Object> model, Client user) {
        guiService.userSpecificModelPopulation(model, user);
        model.put("new-order-details", Order.getOrderInputDescriptions());
        model.put("car-classes", Car.Class.values());
        model.put("client-balance", user.getMoney());
        model.put("username", user.getUsername());
        model.put("current-locale", user.getLocale());
    }

    protected String getView() {
        return "forward:/appPages/neworder.jsp";
    }
}
