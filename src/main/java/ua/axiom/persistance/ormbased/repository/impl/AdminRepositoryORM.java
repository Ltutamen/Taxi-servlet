package ua.axiom.persistance.ormbased.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Admin;
import ua.axiom.persistance.dao.AdminDao;
import ua.axiom.persistance.ormbased.SessionFactoryProvider;
import ua.axiom.persistance.ormbased.repository.AbstractORMRepository;

public class AdminRepositoryORM extends AbstractORMRepository<Long, Admin> implements AdminDao {

    @Autowired
    private SessionFactoryProvider sessionFactoryProvider;

    private SessionFactory sessionFactory;

    @InitMethod
    private void init() {
        sessionFactory = sessionFactoryProvider.getSessionFactory();
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    protected Class<Admin> getEntryType() {
        return Admin.class;
    }
}
