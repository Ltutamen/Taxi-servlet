package ua.axiom.persistance.ormbased.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.persistance.dao.CRUDRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractORMRepository<K extends Serializable, T>  implements CRUDRepository<K, T> {
    protected abstract Session getSession();

    protected abstract Class<T> getEntryType();

    @Override
    public T find(K key) {
        return null;
    }

    @Override
    public void save(T obj) {
        Session session = getSession();
        session.beginTransaction();

        session.persist(obj);

        session.getTransaction().commit();
    }

    @Override
    public T read(K key) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();

        T obj = session.get(getEntryType(), key);

        tx.commit();

        return obj;
    }

    @Override
    public void update(T obj) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();

        session.update(obj);

        tx.commit();
        session.close();
    }

    @Override
    public void delete(K key) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();

        T obj = session.get(getEntryType(), key);
        session.delete(obj);

        tx.commit();
        session.close();
    }

    @Override
    public List<T> findPage(int page, int pSize) {
        throw new NotImplementedException();
    }
}
