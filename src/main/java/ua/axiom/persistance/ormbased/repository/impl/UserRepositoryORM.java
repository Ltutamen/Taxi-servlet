package ua.axiom.persistance.ormbased.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.Component;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.model.actors.User;
import ua.axiom.persistance.dao.UserDao;
import ua.axiom.persistance.ormbased.SessionFactoryProvider;
import ua.axiom.persistance.ormbased.repository.AbstractORMRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryORM extends AbstractORMRepository<Long, User> implements UserDao {

    @Autowired
    private SessionFactoryProvider sessionFactoryProvider;

    private SessionFactory sessionFactory;

    @InitMethod
    private void init() {
        sessionFactory = sessionFactoryProvider.getSessionFactory();
    }

    public Optional<User> getByUsername(String username) {
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Query<User> query = session.createQuery(cq);

        List<User> result = query.getResultList();

        if(result.size() == 1) {
            return Optional.of(result.iterator().next());
        }
        if(result.size() != 0) {
            throw new RuntimeException();
        }
        return Optional.empty();

    }

    @Override
    protected Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    protected Class<User> getEntryType() {
        return User.class;
    }
}
