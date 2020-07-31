package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.factories.CarFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindByKeys;
import ua.axiom.persistance.query.FindOneQuery;
import ua.axiom.persistance.query.InQuery;
import ua.axiom.persistance.repository.AbstractRepository;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;

public class CarRepository extends AbstractRepository<Long, Car> {
    public CarRepository() {
        super(
                new FindAllQuery<>(new CarFactory(), "car", Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new CarFactory(), "car", "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(null, null, null),
                new AbstractMap.SimpleEntry<>(
                        Arrays.asList("driver_id"),
                        new FindByKeys<>(
                                new CarFactory(),
                                "car",
                                Collections.singletonList("driver_id"),
                                Context.get(SimpleDBConnectionProvider.class)))
        );
    }

    @Override
    public Class<Car> getPersistedClass() {
        return Car.class;
    }
}
