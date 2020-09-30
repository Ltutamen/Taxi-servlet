package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.factories.DriverFactory;
import ua.axiom.persistance.dao.DriverDao;
import ua.axiom.persistance.jdbcbased.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.jdbcbased.query.*;
import ua.axiom.persistance.jdbcbased.repository.AbstractRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Component
public class DriverRepositoryJDBC implements DriverDao {
    private static final String DRIVERS_TABLE_NAME = "drivers";

    private final AbstractRepository<Long, Driver> repositoryImpl = new AbstractRepository<>();

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private DriverFactory factory;

    @InitMethod
    private void initMethod() {
        repositoryImpl.setFindAllQuery(new FindAllQuery<>(factory, DRIVERS_TABLE_NAME, connectionProvider));
        repositoryImpl.setSelectByIdQuery(new FindOneByKey<>(factory, DRIVERS_TABLE_NAME, "id", connectionProvider));
        repositoryImpl.setSaveNewQuery(new InQuery<>(null,  null));
        repositoryImpl.setUpdateQuery(new UpdateQuery<>(DRIVERS_TABLE_NAME, "id", Driver.class, connectionProvider));
        repositoryImpl.setFindByFieldsQuery(new FindByKeysQuery<>(factory, DRIVERS_TABLE_NAME, connectionProvider));
    }

    @Override
    public void save(Driver obj) {
        repositoryImpl.save(obj);
    }

    @Override
    public Driver read(Long key) {
        return repositoryImpl.read(key);
    }

    @Override
    public void update(Driver obj, Field[] fields) {
        repositoryImpl.update(obj, fields);
    }

    @Override
    public void delete(Long key) {
        repositoryImpl.delete(key);
    }

    @Override
    public List<Driver> findAll() {
        return repositoryImpl.findAll();
    }

    @Override
    public List<Driver> findByKey(String keyName, String keyValue) {
        return repositoryImpl.findByKey(keyName, keyValue);
    }

    @Override
    public List<Driver> findByFields(Map<String, Object> keyToValueMap) {
        return repositoryImpl.findByFields(keyToValueMap, getPersistedClass());
    }

    @Override
    public Class<Driver> getPersistedClass() {
        return Driver.class;
    }
}
