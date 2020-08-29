package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Client;
import ua.axiom.model.exception.NotEnoughMoneyException;
import ua.axiom.service.InputValidationService;
import ua.axiom.service.InputValidationService.InputType;
import ua.axiom.service.SessionContextService;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewOrderPostCommand extends Command<Client> {
    private InputValidationService inputValidationService;
    private OrderService orderService;

    {
        inputValidationService = Context.get(InputValidationService.class);
        orderService = Context.get(OrderService.class);
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        Client user = SessionContextService.getPersistedCurrentUser(request.getSession());
        Car.Class aClass = Car.Class.valueOf(request.getParameter("aClass"));

        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");

        try {
            orderService.addNewOrder(user, departure, destination, aClass);
        } catch (NotEnoughMoneyException neme) {
            return "redirect:/error?err=neme";
        }

        return "redirect:/clientpage";
    }
}
