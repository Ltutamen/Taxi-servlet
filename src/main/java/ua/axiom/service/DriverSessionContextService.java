package ua.axiom.service;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.model.actors.Order;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Component
public class DriverSessionContextService {
    @Autowired
    private OrderService orderService;

    public boolean hasOrder(HttpSession session) {
        long driverId = SessionContextService.getCurrentUserId(session);

        Collection<Order> orders = orderService.getOrderForDriverAndStatus(driverId, Order.Status.TAKEN);

        return orders.size() == 1 && orders.iterator().next().getDriverId() == driverId;
    }
}
