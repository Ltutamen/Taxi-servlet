package ua.axiom.persistance.repository;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.factories.DriverFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindOneQuery;

//  todo refactor constructor - remove "new" calls
public class DriverRepository extends AbstractRepository<Long, Driver> {
    public DriverRepository() {
        super(
                new FindAllQuery<>(new DriverFactory(), "drivers", Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new DriverFactory(), "drivers", "id", Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new DriverFactory(), "drivers", "username", Context.get(SimpleDBConnectionProvider.class))
        );
    }

    @Override
    public Class<Driver> getPersistedClass() {
        return Driver.class;
    }
}
