package ua.axiom.persistance.dao;

import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Order;

import java.util.Collection;

public interface OrderDao extends CRUDRepository<Long, Order> {
    Collection<Order> getByStatusAndCarClass(Order.Status status, Car.Class cClass);

    Collection<Order> getByDriverAndStatus(Long driverId, Order.Status status);

    Collection<Order> getByClientAndStatus(Long clientId, Order.Status status);
}
