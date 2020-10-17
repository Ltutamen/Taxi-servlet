package ua.axiom.service.buisness;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.dao.CarDao;

import java.util.Collection;

@Component
public class ClientPageService {
    @Autowired
    private CarDao clientRepository;
    @Autowired
    private OrderService orderService;

    public Collection<Order> getTakenOrdersForClientId(long clientID) {
        return orderService.getClientTakenOrders(clientID);

    }

    public Collection<Order> getPendingOrdersByClientId(long clientID) {
        return orderService.getClientPendingOrders(clientID);
    }
}
