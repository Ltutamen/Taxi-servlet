package ua.axiom.persistance.jdbcbased.repository.impl;

import ua.axiom.core.annotations.Bean;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Admin;
import ua.axiom.model.actors.factories.AdminFactory;
import ua.axiom.persistance.dao.AdminDao;
import ua.axiom.persistance.jdbcbased.database.SimpleDBConnectionProvider;
import ua.axiom.persistance.jdbcbased.query.*;
import ua.axiom.persistance.jdbcbased.repository.AbstractRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Bean
public class AdminRepositoryJDBC implements AdminDao {
    private static final String ADMINS_TABLE_NAME = "admins";

    private final AbstractRepository<Long, Admin> repositoryImpl = new AbstractRepository<>();

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private AdminFactory factory;

    @InitMethod
    private void initMethod() {
        repositoryImpl.setFindAllQuery(new FindAllQuery<>(factory, ADMINS_TABLE_NAME, connectionProvider));
        repositoryImpl.setSelectByIdQuery(new FindOneByKey<>(factory, ADMINS_TABLE_NAME, "id", connectionProvider));
        repositoryImpl.setSaveNewQuery(new InQuery<>(null,  null));
        repositoryImpl.setUpdateQuery(new UpdateQuery<>(ADMINS_TABLE_NAME, "id", Admin.class, connectionProvider));
        repositoryImpl.setFindByFieldsQuery(new FindByKeysQuery<>(factory, ADMINS_TABLE_NAME, connectionProvider));
    }

    @Override
    public void save(Admin obj) {
        repositoryImpl.save(obj);
    }

    @Override
    public Admin read(Long key) {
        return repositoryImpl.read(key);
    }

    @Override
    public void update(Admin obj, Field[] fields) {
        repositoryImpl.update(obj, fields);
    }

    @Override
    public void delete(Long key) {
        repositoryImpl.delete(key);
    }

    @Override
    public List<Admin> findAll() {
        return repositoryImpl.findAll();
    }

    @Override
    public List<Admin> findByKey(String keyName, String keyValue) {
        return repositoryImpl.findByKey(keyName, keyValue);
    }

    @Override
    public List<Admin> findByFields(Map<String, Object> keyToValueMap) {
        return repositoryImpl.findByFields(keyToValueMap, getPersistedClass());
    }

    @Override
    public Class<Admin> getPersistedClass() {
        return Admin.class;
    }
}
