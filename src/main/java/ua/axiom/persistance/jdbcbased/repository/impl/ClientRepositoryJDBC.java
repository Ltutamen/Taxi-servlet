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

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Component
public class ClientRepositoryJDBC implements ClientDao {
    private static String CLIENTS_TABLE_NAME = "clients";

    private final AbstractRepository<Long, Client> repositoryImpl = new AbstractRepository<>();

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private ClientFactory factory;

    @InitMethod
    private void initMethod() {
        repositoryImpl.setFindAllQuery(new FindAllQuery<>(factory, CLIENTS_TABLE_NAME, connectionProvider));
        repositoryImpl.setSelectByIdQuery(new FindOneByKey<>(factory, CLIENTS_TABLE_NAME, "id", connectionProvider));
        repositoryImpl.setSaveNewQuery(new InQuery<>(null, null));
        repositoryImpl.setUpdateQuery(new UpdateQuery<>(CLIENTS_TABLE_NAME, "id", Client.class, connectionProvider));
        repositoryImpl.setFindByFieldsQuery(new FindByKeysQuery<>(factory, CLIENTS_TABLE_NAME, connectionProvider));
    }

    @Override
    public Class<Client> getPersistedClass() {
        return Client.class;
    }

    @Override
    public void save(Client obj) {
        repositoryImpl.save(obj);
    }

    @Override
    public Client read(Long key) {
        return repositoryImpl.read(key);
    }

    @Override
    public void update(Client obj, Field[] fields) {
        repositoryImpl.update(obj, fields);
    }

    @Override
    public void delete(Long key) {
        repositoryImpl.delete(key);
    }

    @Override
    public List<Client> findAll() {
        return repositoryImpl.findAll();
    }

    @Override
    public List<Client> findByKey(String keyName, String keyValue) {
        return repositoryImpl.findByKey(keyName, keyValue);
    }

    @Override
    public List<Client> findByFields(Map<String, Object> keyToValueMap) {
        return repositoryImpl.findByFields(keyToValueMap, getPersistedClass());
    }
}
