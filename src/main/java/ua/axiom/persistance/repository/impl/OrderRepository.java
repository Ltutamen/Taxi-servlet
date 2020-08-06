package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Order;
import ua.axiom.model.actors.factories.OrderFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.*;
import ua.axiom.persistance.repository.AbstractRepository;

import java.util.AbstractMap;
import java.util.Arrays;


public class OrderRepository extends AbstractRepository<Long, Order> {
    private static final String ORDERS_TABLE_NAME = "orders";

    public OrderRepository() {
        super(
                new FindAllQuery<>(new OrderFactory(), ORDERS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new OrderFactory(), ORDERS_TABLE_NAME, "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(ORDERS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class)),
                new UpdateQuery<>(ORDERS_TABLE_NAME, "id", Order.class, Context.get(SimpleDBConnectionProvider.class)),
                new AbstractMap.SimpleEntry<>(
                        Arrays.asList("c_class", "status"),
                        new FindByKeys<>(
                                new OrderFactory(),
                                "orders",
                                Arrays.asList("c_class", "status"),
                                Context.get(SimpleDBConnectionProvider.class)
                        )
                ),
                new AbstractMap.SimpleEntry<>(
                        Arrays.asList("client_id", "status"),
                        new FindByKeys<>(
                                new OrderFactory(),
                                "orders",
                                Arrays.asList("client_id", "status"),
                                Context.get(SimpleDBConnectionProvider.class)
                        )
                )

        );
    }

    @Override
    public Class<Order> getPersistedClass() {
        return Order.class;
    }
}
