package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Driver;
import ua.axiom.model.actors.factories.DriverFactory;
import ua.axiom.persistance.jdbcbased.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.jdbcbased.query.*;
import ua.axiom.persistance.jdbcbased.repository.AbstractRepository;

@Component
public class DriverRepository extends AbstractRepository<Long, Driver> {
    private static final String DRIVERS_TABLE_NAME = "drivers";

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private DriverFactory factory;

    @InitMethod
    private void initMethod() {
        super.setFindAllQuery(new FindAllQuery<>(factory, DRIVERS_TABLE_NAME, connectionProvider));
        super.setSelectByIdQuery(new FindOneByKey<>(factory, DRIVERS_TABLE_NAME, "id", connectionProvider));
        super.setSaveNewQuery(new InQuery<>(null,  null));
        super.setUpdateQuery(new UpdateQuery<>(DRIVERS_TABLE_NAME, "id", Driver.class, connectionProvider));
        super.setFindByFieldsQuery(new FindByKeysQuery<>(factory, DRIVERS_TABLE_NAME, connectionProvider));
    }
    @Override
    public Class<Driver> getPersistedClass() {
        return Driver.class;
    }
}
