package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Order;
import ua.axiom.model.actors.factories.OrderFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindByKeys;
import ua.axiom.persistance.query.FindOneQuery;
import ua.axiom.persistance.query.InQuery;
import ua.axiom.persistance.repository.AbstractRepository;

import java.util.AbstractMap;
import java.util.Arrays;


public class OrderRepository extends AbstractRepository<Long, Order> {

    public OrderRepository() {
        super(
                new FindAllQuery<>(new OrderFactory(), "orders", Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new OrderFactory(), "orders", "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(
                        "orders",
                        new String[]{
                                "id",
                                "client_id",
                                "driver_id",
                                "status",
                                "price",
                                "date",
                                "cClass",
                                "departure",
                                "destination",
                                "confirmedByClient",
                                "confirmedByDriver"
                        },
                        Context.get(SimpleDBConnectionProvider.class)
                ),
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
