package ua.axiom.persistance.repository.impl;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Bean;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.factories.CarFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.*;
import ua.axiom.persistance.repository.AbstractRepository;

@Bean
public class CarRepository extends AbstractRepository<Long, Car> {
    private static final String CARS_TABLE_NAME = "car";

    public CarRepository() {
    }

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private CarFactory factory;

    @InitMethod
    private void initMethod() {
        super.setFindAllQuery(new FindAllQuery<>(factory, CARS_TABLE_NAME, connectionProvider));
        super.setSelectByIdQuery(new FindOneByKey<>(factory, CARS_TABLE_NAME, "id", connectionProvider));
        super.setSaveNewQuery(new InQuery<>(null, null));
        super.setUpdateQuery(new UpdateQuery<>(CARS_TABLE_NAME, "id", Car.class, connectionProvider));
        super.setFindByFieldsQuery(new FindByKeysQuery<>(factory, CARS_TABLE_NAME, connectionProvider));
    }

    @Override
    public Class<Car> getPersistedClass() {
        return Car.class;
    }
}
