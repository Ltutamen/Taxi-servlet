package ua.axiom.persistance.dao;

import java.util.List;

public interface CRUDRepository<K, T> {

    void save(T obj);

    T read(K key);

    void update(T obj);

    void delete(K key);

    /**
     * @param page - number of page, starts from 1
     * @param pSize - size of single page
     * @return list of Items on page
     */
    List<T> findPage(int page, int pSize);

    T find(K key);
}
