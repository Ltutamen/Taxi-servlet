package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Order;
import ua.axiom.model.actors.factories.OrderFactory;
import ua.axiom.persistance.dao.OrderDao;
import ua.axiom.persistance.jdbcbased.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.jdbcbased.query.*;
import ua.axiom.persistance.jdbcbased.repository.AbstractRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Component
public class OrderRepositoryJDBC implements OrderDao {
    private static final String ORDERS_TABLE_NAME = "orders";

    private final AbstractRepository<Long, Order> repositoryImpl = new AbstractRepository<>();

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private OrderFactory factory;

    @InitMethod
    private void initMethod() {
        repositoryImpl.setFindAllQuery(new FindAllQuery<>(factory, ORDERS_TABLE_NAME, connectionProvider));
        repositoryImpl.setSelectByIdQuery(new FindOneByKey<>(factory, ORDERS_TABLE_NAME, "id", connectionProvider));
        repositoryImpl.setSaveNewQuery(new InQuery<>(null,  null));
        repositoryImpl.setUpdateQuery(new UpdateQuery<>(ORDERS_TABLE_NAME, "id", Order.class, connectionProvider));
        repositoryImpl.setFindByFieldsQuery(new FindByKeysQuery<>(factory, ORDERS_TABLE_NAME, connectionProvider));
    }

    @Override
    public void save(Order obj) {
        repositoryImpl.save(obj);
    }

    @Override
    public Order read(Long key) {
        return repositoryImpl.read(key);
    }

    @Override
    public void update(Order obj, Field[] fields) {
        repositoryImpl.update(obj, fields);
    }

    @Override
    public void delete(Long key) {
        repositoryImpl.delete(key);
    }

    @Override
    public List<Order> findAll() {
        return repositoryImpl.findAll();
    }

    @Override
    public List<Order> findByKey(String keyName, String keyValue) {
        return repositoryImpl.findByKey(keyName, keyValue);
    }

    @Override
    public List<Order> findByFields(Map<String, Object> keyToValueMap) {
        return repositoryImpl.findByFields(keyToValueMap, getPersistedClass());
    }

    @Override
    public Class<Order> getPersistedClass() {
        return Order.class;
    }
}
