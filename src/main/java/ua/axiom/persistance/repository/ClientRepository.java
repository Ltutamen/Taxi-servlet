package ua.axiom.persistance.repository;

import ua.axiom.core.Context;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.factories.ClientFactory;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.query.FindAllQuery;
import ua.axiom.persistance.query.FindOneQuery;

import java.util.AbstractMap;

public class ClientRepository extends AbstractRepository<Long, Client> {

    public ClientRepository() {
        super(
            new FindAllQuery<>(new ClientFactory(), "clients", Context.get(SimpleDBConnectionProvider.class)),
            new FindOneQuery<>(new ClientFactory(), "clients", "id", Context.get(SimpleDBConnectionProvider.class)),
            new AbstractMap.SimpleEntry<>("username", new FindOneQuery<>(new ClientFactory(), "clients", "username", Context.get(SimpleDBConnectionProvider.class)))
        );
    }

    @Override
    public Class<Client> getPersistedClass() {
        return Client.class;
    }
}
