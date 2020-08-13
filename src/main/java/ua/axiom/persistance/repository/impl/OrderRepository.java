package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Order;
import ua.axiom.model.actors.factories.OrderFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.*;
import ua.axiom.persistance.repository.AbstractRepository;


public class OrderRepository extends AbstractRepository<Long, Order> {
    private static final String ORDERS_TABLE_NAME = "orders";

    public OrderRepository() {
        super(
                new FindAllQuery<>(new OrderFactory(), ORDERS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new OrderFactory(), ORDERS_TABLE_NAME, "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(ORDERS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class)),
                new UpdateQuery<>(ORDERS_TABLE_NAME, "id", Order.class, Context.get(SimpleDBConnectionProvider.class)),
                new FindByKeysQuery<>(new OrderFactory(), ORDERS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class))

        );
    }

    @Override
    public Class<Order> getPersistedClass() {
        return Order.class;
    }
}
