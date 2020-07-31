package ua.axiom.service.buisness;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.query.IdGenerationQuery;
import ua.axiom.persistance.query.OutQuery;
import ua.axiom.persistance.repository.impl.ClientRepository;
import ua.axiom.persistance.repository.impl.DriverRepository;
import ua.axiom.persistance.repository.impl.OrderRepository;

public class OrderService {
    private OrderRepository orderRepository;
    private ClientRepository clientRepository;
    private DriverRepository driverRepository;

    private OutQuery<Void, Long> idGenerationQuery;

    {
        orderRepository = Context.get(OrderRepository.class);
        clientRepository = Context.get(ClientRepository.class);
        driverRepository = Context.get(DriverRepository.class);
        idGenerationQuery = Context.get(IdGenerationQuery.class);
    }

    public void addNewOrder(Client user, String departure, String destination, Car.Class aClass) {
        Order order = new Order(idGenerationQuery.execute(null).iterator().next());
        order.setStatus(Order.Status.PENDING);
        order.setDestination(destination);
        order.setDeparture(departure);
        order.setcClass(aClass);

        orderRepository.save(order, order.getId());
    }

    public void finishOrder(Order order) {
        order.setStatus(Order.Status.FINISHED);

        Driver driver = driverRepository.findOne(order.getDriver_id()).iterator().next();

        driver.setMoney(driver.getMoney().add(order.getPrice()));
        driver.setCurrentOrderId(null);

        driverRepository.save(driver, driver.getId());
        orderRepository.save(order, driver.getId());

    }
}
