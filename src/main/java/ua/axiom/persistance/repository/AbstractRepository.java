package ua.axiom.persistance.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.query.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Abstract repository, that supports basic CRUD operations with a database over a specific table
 * @param <K> key of the T
 * @param <T> table entity type
 */
//  todo remove field to T name mapping, use field name instead
public abstract class AbstractRepository<K, T extends Persistent<K>> {

    private FindAllQuery<K, T> findAllQuery;
    private FindOneByKey<K, T> selectByIdQuery;

    private InQuery<K, T> saveNewQuery;
    private UpdateQuery<K, T> updateQuery;
    //  maps field names to set of queries, that take unknown Key and produce T
    private FindByKeysQuery<K, T> findByFieldsQuery;

    protected AbstractRepository() {
    }

    protected void setFindAllQuery(FindAllQuery<K, T> findQuery) {
        this.findAllQuery = findQuery;
    }

    protected void setSelectByIdQuery(FindOneByKey<K, T> selectQuery) {
        this.selectByIdQuery = selectQuery;
    }

    protected void setSaveNewQuery(InQuery<K, T> saveOneQuery) {
        this.saveNewQuery = saveOneQuery;
    }

    protected void setUpdateQuery(UpdateQuery<K, T> updateQuery) {
        this.updateQuery = updateQuery;
    }

    protected void setFindByFieldsQuery(FindByKeysQuery<K, T> findByFieldsQuery) {
        this.findByFieldsQuery = findByFieldsQuery;
    }

    public List<T> findAll() {
        return findAllQuery.execute(null);
    }

    public List<T> findOne(K id) {
        return selectByIdQuery.execute(id);
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

    public List<T> findByFields(Map<String, Object> keyToValueMap) {
        return findByFieldsQuery.execute(getPersistedClass(), keyToValueMap);
    }

    public abstract Class<T> getPersistedClass();

}
