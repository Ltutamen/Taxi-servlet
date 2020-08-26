package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.factories.CarFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.*;
import ua.axiom.persistance.repository.AbstractRepository;

public class CarRepository extends AbstractRepository<Long, Car> {
    private static final String CARS_TABLE_NAME = "car";

    public CarRepository() {
        super(
                new FindAllQuery<>(new CarFactory(), CARS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new CarFactory(), CARS_TABLE_NAME, "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(null, null),
                new UpdateQuery<>(CARS_TABLE_NAME, "id", Car.class, Context.get(SimpleDBConnectionProvider.class)),
                new FindByKeysQuery<>(new CarFactory(), CARS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class))
        );
    }

    @Override
    public Class<Car> getPersistedClass() {
        return Car.class;
    }
}
