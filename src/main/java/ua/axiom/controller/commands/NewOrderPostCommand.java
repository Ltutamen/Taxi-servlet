package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Client;
import ua.axiom.model.exception.NotEnoughMoneyException;
import ua.axiom.service.InputValidationService;
import ua.axiom.service.SessionContextService;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/neworder")
public class NewOrderPostCommand extends Command<Client> {
    @Autowired
    private InputValidationService inputValidationService;
    @Autowired
    private OrderService orderService;

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
