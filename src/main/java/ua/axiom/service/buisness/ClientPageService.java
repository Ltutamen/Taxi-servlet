package ua.axiom.service.buisness;

import ua.axiom.model.actors.Order;
import ua.axiom.persistance.repository.impl.ClientRepository;

import java.util.List;

public class ClientPageService {
    private ClientRepository clientRepository;
    private OrderService orderService;

    public ClientPageService(ClientRepository clientRepository, OrderService orderService) {
        this.clientRepository = clientRepository;
        this.orderService = orderService;
    }

    public List<Order> getTakenOrdersForClientId(long clientID) {
        return orderService.getClientTakenOrders(clientID);

    }

    public List<Order> getPendingOrdersByClientId(long clientID) {
        return orderService.getClientPendingOrders(clientID);
    }
}
