package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.actors.Driver;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverPageOrderConfirmation extends Command<Driver> {
    private OrderService orderService;

    {
        this.orderService = Context.get(OrderService.class);
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        orderService.confirmByDriver(Long.parseLong(request.getParameter("orderID")));
        return "redirect:/driverpage";
    }


}
