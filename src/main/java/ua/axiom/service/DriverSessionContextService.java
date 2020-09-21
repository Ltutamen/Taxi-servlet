package ua.axiom.service;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.actors.Order;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class DriverSessionContextService {
    @Autowired
    private OrderService orderService;

    public boolean hasOrder(HttpSession session) {
        long driverId = SessionContextService.getCurrentUserId(session);

        Optional<Order> order = orderService.getOrderForDriver(driverId);

        return order.isPresent() && order.get().getDriver_id() == driverId;

    }
}
