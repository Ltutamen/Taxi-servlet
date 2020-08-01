package ua.axiom.persistance.repository.impl;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Admin;
import ua.axiom.model.actors.factories.AdminFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindByKeys;
import ua.axiom.persistance.query.FindOneQuery;
import ua.axiom.persistance.query.InQuery;
import ua.axiom.persistance.repository.AbstractRepository;

import java.util.AbstractMap;
import java.util.Collections;

public class AdminRepository extends AbstractRepository<Long, Admin> {

    public AdminRepository() {
        super(
                new FindAllQuery<>(new AdminFactory(), "admins", Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new AdminFactory(), "admins", "id", Context.get(SimpleDBConnectionProvider.class)),
                new InQuery<>(null,  null),
                new AbstractMap.SimpleEntry<>(
                        Collections.singletonList("username"),
                        new FindByKeys<>(
                                new AdminFactory(),
                                "admins",
                                Collections.singletonList("username"),
                                Context.get(SimpleDBConnectionProvider.class)))
        );
    }

    @Override
    public Class<Admin> getPersistedClass() {
        return Admin.class;
    }
}
