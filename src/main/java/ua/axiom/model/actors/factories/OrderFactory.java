package ua.axiom.model.actors.factories;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.Fabricable;

public class OrderFactory implements Fabricable<Order> {
    @Override
    public Order fabricate(String[] params) {
        throw new NotImplementedException();
    }
}
