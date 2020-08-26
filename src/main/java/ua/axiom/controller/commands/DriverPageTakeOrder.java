package ua.axiom.controller.commands;

import ua.axiom.controller.Command;
import ua.axiom.core.Context;
import ua.axiom.model.actors.Driver;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverPageTakeOrder extends Command<Driver> {
    private OrderService orderService;

    {
        orderService = Context.get(OrderService.class);
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        long driverId = getCurrentUserId(request);
        long orderId = Long.parseLong(request.getParameter("orderId"));

        orderService.processOrderTakenByDriver(driverId, orderId);

        return "redirect:/driverpage";
    }
}
