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

@Bean
public class AdminRepository extends AdminDao {
    private static final String ADMINS_TABLE_NAME = "admins";

    @Autowired
    private SimpleDBConnectionProvider connectionProvider;
    @Autowired
    private AdminFactory factory;

    @InitMethod
    private void initMethod() {
        super.setFindAllQuery(new FindAllQuery<>(factory, ADMINS_TABLE_NAME, connectionProvider));
        super.setSelectByIdQuery(new FindOneByKey<>(factory, ADMINS_TABLE_NAME, "id", connectionProvider));
        super.setSaveNewQuery(new InQuery<>(null,  null));
        super.setUpdateQuery(new UpdateQuery<>(ADMINS_TABLE_NAME, "id", Admin.class, connectionProvider));
        super.setFindByFieldsQuery(new FindByKeysQuery<>(factory, ADMINS_TABLE_NAME, connectionProvider));
    }

    @Override
    public Class<Admin> getPersistedClass() {
        return Admin.class;
    }
}
