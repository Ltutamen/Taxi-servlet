package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Bean;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.factories.CarFactory;
import ua.axiom.persistance.dao.CarDao;
import ua.axiom.persistance.jdbcbased.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.jdbcbased.query.*;
import ua.axiom.persistance.jdbcbased.repository.AbstractRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Bean
public class CarRepositoryJDBC implements CarDao {
    private static final String CARS_TABLE_NAME = "car";

    private final AbstractRepository<Long, Car> repositoryImpl = new AbstractRepository<>();

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private CarFactory factory;

    @InitMethod
    private void initMethod() {
        repositoryImpl.setFindAllQuery(new FindAllQuery<>(factory, CARS_TABLE_NAME, connectionProvider));
        repositoryImpl.setSelectByIdQuery(new FindOneByKey<>(factory, CARS_TABLE_NAME, "id", connectionProvider));
        repositoryImpl.setSaveNewQuery(new InQuery<>(null, null));
        repositoryImpl.setUpdateQuery(new UpdateQuery<>(CARS_TABLE_NAME, "id", Car.class, connectionProvider));
        repositoryImpl.setFindByFieldsQuery(new FindByKeysQuery<>(factory, CARS_TABLE_NAME, connectionProvider));
    }

    @Override
    public void save(Car obj) {
        repositoryImpl.save(obj);
    }

    @Override
    public Car read(Long key) {
        return repositoryImpl.read(key);
    }

    @Override
    public void update(Car obj, Field[] fields) {
        repositoryImpl.update(obj, fields);
    }

    @Override
    public void delete(Long key) {
        repositoryImpl.delete(key);
    }

    @Override
    public List<Car> findAll() {
        return repositoryImpl.findAll();
    }

    @Override
    public List<Car> findByKey(String keyName, String keyValue) {
        return repositoryImpl.findByKey(keyName, keyValue);
    }

    @Override
    public List<Car> findByFields(Map<String, Object> keyToValueMap) {
        return repositoryImpl.findByFields(keyToValueMap, getPersistedClass());
    }

    @Override
    public List<Car> findAll(int page, int onPage) {
        return repositoryImpl.findAll(page, onPage);
    }

    @Override
    public Class<Car> getPersistedClass() {
        return Car.class;
    }
}
