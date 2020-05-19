package ua.axiom.repository;

import java.util.List;

public abstract class AbstractRepository<K, T> {
    public abstract List<T> findAll();

    public abstract List<T> findOne(K id);

    public abstract void save(T object);

    public abstract void delete(K id);

    public abstract List<T> findAll(int page, int onPage);

}
