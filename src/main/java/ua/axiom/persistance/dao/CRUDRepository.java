package ua.axiom.persistance.dao;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.persistance.jdbcbased.Persistent;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface CRUDRepository<K, T extends Persistent<K>> {
    void save(T obj);

    T read(K key);

    void update(T obj, Field[] fields);

    void delete(K key);

    //  todo replace with pagination
    List<T> findAll();

    List<T> findByKey(String keyName, String keyValue);

    //  todo
    default List<T> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }

    List<T> findByFields(Map<String, Object> keyToValueMap);

    Class<T> getPersistedClass();

}
