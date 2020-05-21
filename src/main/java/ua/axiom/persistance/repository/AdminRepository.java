package ua.axiom.persistance.repository;

import ua.axiom.model.actors.Admin;
import ua.axiom.model.actors.factories.AdminFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindOneQuery;

public class AdminRepository extends AbstractRepository<Long, Admin> {

    public AdminRepository() {
        super(
                new FindAllQuery<>(new AdminFactory(), "admins", new SimpleDBConnectionProvider()),
                new FindOneQuery<>(new AdminFactory(), "admins", "id", new SimpleDBConnectionProvider()),
                new FindOneQuery<>(new AdminFactory(), "admins", "username", new SimpleDBConnectionProvider()));
    }

    @Override
    public Class<Admin> getPersistedClass() {
        return Admin.class;
    }
}
