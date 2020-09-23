package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Order;
import ua.axiom.model.actors.factories.OrderFactory;
import ua.axiom.persistance.jdbcbased.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.jdbcbased.query.*;
import ua.axiom.persistance.jdbcbased.repository.AbstractRepository;

@Component
public class OrderRepository extends AbstractRepository<Long, Order> {
    private static final String ORDERS_TABLE_NAME = "orders";

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private OrderFactory factory;

    @InitMethod
    private void initMethod() {
        super.setFindAllQuery(new FindAllQuery<>(factory, ORDERS_TABLE_NAME, connectionProvider));
        super.setSelectByIdQuery(new FindOneByKey<>(factory, ORDERS_TABLE_NAME, "id", connectionProvider));
        super.setSaveNewQuery(new InQuery<>(null,  null));
        super.setUpdateQuery(new UpdateQuery<>(ORDERS_TABLE_NAME, "id", Order.class, connectionProvider));
        super.setFindByFieldsQuery(new FindByKeysQuery<>(factory, ORDERS_TABLE_NAME, connectionProvider));
    }

    @Override
    public Class<Order> getPersistedClass() {
        return Order.class;
    }
}
