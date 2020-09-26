package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.factories.ClientFactory;
import ua.axiom.persistance.dao.ClientDao;
import ua.axiom.persistance.jdbcbased.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.jdbcbased.query.*;
import ua.axiom.persistance.jdbcbased.repository.AbstractRepository;

@Component
public class ClientRepository extends ClientDao {
    private static String CLIENTS_TABLE_NAME = "clients";

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private ClientFactory factory;

    @InitMethod
    private void initMethod() {
        super.setFindAllQuery(new FindAllQuery<>(factory, CLIENTS_TABLE_NAME, connectionProvider));
        super.setSelectByIdQuery(new FindOneByKey<>(factory, CLIENTS_TABLE_NAME, "id", connectionProvider));
        super.setSaveNewQuery(new InQuery<>(null, null));
        super.setUpdateQuery(new UpdateQuery<>(CLIENTS_TABLE_NAME, "id", Client.class, connectionProvider));
        super.setFindByFieldsQuery(new FindByKeysQuery<>(factory, CLIENTS_TABLE_NAME, connectionProvider));
    }

    @Override
    public Class<Client> getPersistedClass() {
        return Client.class;
    }
}
