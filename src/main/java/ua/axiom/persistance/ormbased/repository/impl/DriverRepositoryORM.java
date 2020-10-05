package ua.axiom.persistance.ormbased.repository.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Driver;
import ua.axiom.persistance.dao.DriverDao;
import ua.axiom.persistance.ormbased.SessionFactoryProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Component
public class DriverRepositoryORM implements DriverDao {
    @Autowired
    private SessionFactoryProvider sessionFactoryProvider;

    private SessionFactory sessionFactory;

    @InitMethod
    private void init() {
        sessionFactory = sessionFactoryProvider.getSessionFactory();
    }

    @Override
    public void save(Driver obj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(obj);

        session.getTransaction().commit();
    }

    @Override
    public Driver read(Long key) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Driver driver = session.get(getPersistedClass(), key);

        tx.commit();

        return driver;
    }

    @Override
    public void update(Driver obj, Field[] fields) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.update(obj);

        tx.commit();
        sessionFactory.close();
    }

    @Override
    public void delete(Long key) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Driver driver = session.get(getPersistedClass(), key);
        session.delete(driver);

        tx.commit();
        sessionFactory.close();

    }

    @Override
    public List<Driver> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public List<Driver> findByKey(String keyName, String keyValue) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Driver> drivers = session.createQuery("FROM DRIVERS").list();
        tx.commit();
        return drivers;
    }

    @Override
    public List<Driver> findByFields(Map<String, Object> keyToValueMap) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();


        Criteria criteria = session.createCriteria(getPersistedClass());

        //  todo propName is not equal to field name! (eq. CarClass -> car_class)
        keyToValueMap.forEach(
                (propName, value) -> {
                    criteria.add(Restrictions.eq(propName, value));
                }
        );

        return criteria.list();
    }

    @Override
    public Class<Driver> getPersistedClass() {
        return Driver.class;
    }
}
