package ua.axiom.persistance.repository;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Admin;
import ua.axiom.model.actors.factories.AdminFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindOneQuery;

import java.util.AbstractMap;

public class AdminRepository extends AbstractRepository<Long, Admin> {

    public AdminRepository() {
        super(
                new FindAllQuery<>(new AdminFactory(), "admins", Context.get(SimpleDBConnectionProvider.class)),
                new FindOneQuery<>(new AdminFactory(), "admins", "id", Context.get(SimpleDBConnectionProvider.class)),
                new AbstractMap.SimpleEntry<>("username", new FindOneQuery<>(new AdminFactory(), "admins", "username", Context.get(SimpleDBConnectionProvider.class)))
        );
    }

    @Override
    public Class<Admin> getPersistedClass() {
        return Admin.class;
    }
}
