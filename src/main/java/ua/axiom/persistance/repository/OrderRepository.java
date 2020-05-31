package ua.axiom.persistance.repository;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Order;
import ua.axiom.model.actors.factories.OrderFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindByManyKeys;
import ua.axiom.persistance.query.FindOneQuery;

import java.util.AbstractMap;
import java.util.Arrays;

public class OrderRepository extends AbstractRepository<Long, Order> {

    protected OrderRepository() {
        super(
                new FindAllQuery(new OrderFactory(), "orders", Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new OrderFactory(), "orders", "id", Context.get(SimpleDBConnectionProvider.class)),
                new AbstractMap.SimpleEntry<>(
                        Arrays.asList("cClass", "status"),
                        new FindByManyKeys<>(
                                new OrderFactory(),
                                "orders",
                                Arrays.asList("cClass", "status"),
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
