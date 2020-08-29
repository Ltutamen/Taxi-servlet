package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.actors.Client;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientPageOrderConfirmation extends Command<Client> {
    private OrderService orderService;

    {
        orderService = Context.get(OrderService.class);
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        orderService.confirmByClient(Long.parseLong(request.getParameter("orderId")));

        return "redirect:/clientpage";
    }
}
