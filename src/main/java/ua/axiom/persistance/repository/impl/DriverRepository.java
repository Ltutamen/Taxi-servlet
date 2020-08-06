package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.factories.DriverFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.*;
import ua.axiom.persistance.repository.AbstractRepository;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;

//  todo refactor constructor - remove "new" calls
public class DriverRepository extends AbstractRepository<Long, Driver> {
    private static final String DRIVERS_TABLE_NAME = "drivers";

    public DriverRepository() {
        super(
                new FindAllQuery<>(new DriverFactory(), DRIVERS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new DriverFactory(), DRIVERS_TABLE_NAME, "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(null, null),
                new UpdateQuery<>(DRIVERS_TABLE_NAME, "id", Driver.class, Context.get(SimpleDBConnectionProvider.class)),
                new AbstractMap.SimpleEntry<>(
                        Collections.singletonList("username"),
                        new FindByKeys<>(
                                new DriverFactory(),
                                DRIVERS_TABLE_NAME, Collections.singletonList("username"),
                                Context.get(SimpleDBConnectionProvider.class)))
        );
    }

    @Override
    public Class<Driver> getPersistedClass() {
        return Driver.class;
    }
}
