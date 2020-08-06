package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Admin;
import ua.axiom.model.actors.factories.AdminFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.*;
import ua.axiom.persistance.repository.AbstractRepository;

import java.util.AbstractMap;
import java.util.Collections;

public class AdminRepository extends AbstractRepository<Long, Admin> {
    private static final String ADMINS_TABLE_NAME = "admins";

    public AdminRepository() throws NoSuchFieldException {
        super(
                new FindAllQuery<>(new AdminFactory(), ADMINS_TABLE_NAME, Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new AdminFactory(), ADMINS_TABLE_NAME, "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(null,  null),
                new UpdateQuery<>(ADMINS_TABLE_NAME, "id", Admin.class, Context.get(SimpleDBConnectionProvider.class)),
                new AbstractMap.SimpleEntry<>(
                        Collections.singletonList("username"),
                        new FindByKeys<>(
                                new AdminFactory(),
                                ADMINS_TABLE_NAME,
                                Collections.singletonList("username"),
                                Context.get(SimpleDBConnectionProvider.class)))
        );
    }

    @Override
    public Class<Admin> getPersistedClass() {
        return Admin.class;
    }
}
