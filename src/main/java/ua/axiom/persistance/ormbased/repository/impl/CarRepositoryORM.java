package ua.axiom.persistance.ormbased.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Car;
import ua.axiom.persistance.dao.CarDao;
import ua.axiom.persistance.ormbased.SessionFactoryProvider;
import ua.axiom.persistance.ormbased.repository.AbstractORMRepository;

@Component
public class CarRepositoryORM extends AbstractORMRepository<Long, Car> implements CarDao {
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
    protected Class<Car> getEntryType() {
        return Car.class;
    }
}
