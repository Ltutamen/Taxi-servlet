package ua.axiom.persistance.jdbcbased.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.persistance.dao.CRUDRepository;
import ua.axiom.persistance.jdbcbased.Persistent;
import ua.axiom.persistance.jdbcbased.query.*;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Abstract repository, that supports basic CRUD operations with a database over a specific table
 * @param <K> key of the T
 * @param <T> table entity type
 */
//  todo remove field to T name mapping, use field name instead
public class AbstractRepository<K, T extends Persistent<K>> {

    private FindAllQuery<K, T> findAllQuery;
    private FindOneByKey<K, T> selectByIdQuery;
    private InQuery<K, T> saveNewQuery;
    private UpdateQuery<K, T> updateQuery;
    private FindByKeysQuery<K, T> findByFieldsQuery;

    public AbstractRepository() { }

    //  todo replace with pagination
    public List<T> findAll() {
        return findAllQuery.execute(null);
    }

    public T read(K id) {
        Collection<T> result = selectByIdQuery.execute(id);
        return result.size() == 0 ? null : result.iterator().next();
    }

    public List<T> findByKey(String keyName, String keyValue) {
        throw new NotImplementedException();
    }

    public void save(T object) {
        saveNewQuery.execute(object, object.getId());
    }

    public void update(T object, Field[] fields) {
        updateQuery.execute(object, object.getId(), fields);
    }

    public void delete(K id) {
        throw new NotImplementedException();
    }

    public List<T> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }

    public List<T> findByFields(Map<String, Object> keyToValueMap, Class<T> cclass) {
        return findByFieldsQuery.execute(cclass, keyToValueMap);
    }


    public void setFindAllQuery(FindAllQuery<K, T> findQuery) {
        this.findAllQuery = findQuery;
    }

    public void setSelectByIdQuery(FindOneByKey<K, T> selectQuery) {
        this.selectByIdQuery = selectQuery;
    }

    public void setSaveNewQuery(InQuery<K, T> saveOneQuery) {
        this.saveNewQuery = saveOneQuery;
    }

    public void setUpdateQuery(UpdateQuery<K, T> updateQuery) {
        this.updateQuery = updateQuery;
    }

    public void setFindByFieldsQuery(FindByKeysQuery<K, T> findByFieldsQuery) {
        this.findByFieldsQuery = findByFieldsQuery;
    }

}
