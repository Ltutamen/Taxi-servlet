package ua.axiom.persistance.ormbased.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Order;
import ua.axiom.persistance.dao.OrderDao;
import ua.axiom.persistance.ormbased.SessionFactoryProvider;
import ua.axiom.persistance.ormbased.repository.AbstractORMRepository;

import java.util.Collection;

@Component
public class OrderRepositoryORM extends AbstractORMRepository<Long, Order> implements OrderDao {

    @Autowired
    private SessionFactoryProvider sessionFactoryProvider;

    private SessionFactory sessionFactory;

    @Override
    public Collection<Order> getByStatusAndCarClass(Order.Status status, Car.Class cClass) {
        Session session = getSession();

        Query<Order> query = session.createQuery("FROM ORDERS where status = :status AND c_class = :car_calss", Order.class);

        query.setParameter("status", status);
        query.setParameter("car_class", cClass);

        return query.getResultList();

    }

    @Override
    public Collection<Order> getByDriverAndStatus(Long driverId, Order.Status status) {
        Session session = getSession();

        Query<Order> query = session.createQuery("FROM ORDERS where driver_id = :driver_id AND c_class = :car_calss", Order.class);

        query.setParameter("status", status);
        query.setParameter("driver_id", driverId);

        return query.getResultList();
    }

    @Override
    public Collection<Order> getByClientAndStatus(Long clientId, Order.Status status) {
        Session session = getSession();

        Query<Order> query = session.createQuery("FROM ORDERS where client_id = :client_id AND c_class = :car_calss", Order.class);

        query.setParameter("status", status);
        query.setParameter("client_id", clientId);

        return query.getResultList();
    }

    @InitMethod
    private void init() {
        sessionFactory = sessionFactoryProvider.getSessionFactory();
    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    protected Class<Order> getEntryType() {
        return Order.class;
    }
}
