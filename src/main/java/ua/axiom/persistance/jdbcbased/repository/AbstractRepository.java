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
public abstract class AbstractRepository<K, T extends Persistent<K>> implements CRUDRepository<K, T> {

    private FindAllQuery<K, T> findAllQuery;
    private FindOneByKey<K, T> selectByIdQuery;
    private InQuery<K, T> saveNewQuery;
    private UpdateQuery<K, T> updateQuery;
    private FindByKeysQuery<K, T> findByFieldsQuery;

    protected AbstractRepository() { }

    //  todo replace with pagination
    @Override
    public List<T> findAll() {
        return findAllQuery.execute(null);
    }

    @Override
    public T read(K id) {
        Collection<T> result = selectByIdQuery.execute(id);
        return result.size() == 0 ? null : result.iterator().next();
    }

    @Override
    public List<T> findByKey(String keyName, String keyValue) {
        throw new NotImplementedException();
    }

    @Override
    public void save(T object) {
        saveNewQuery.execute(object, object.getId());
    }

    @Override
    public void update(T object, Field[] fields) {
        updateQuery.execute(object, object.getId(), fields);
    }

    @Override
    public void delete(K id) {
        throw new NotImplementedException();
    }

    @Override
    public List<T> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }

    @Override
    public List<T> findByFields(Map<String, Object> keyToValueMap) {
        return findByFieldsQuery.execute(getPersistedClass(), keyToValueMap);
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

}
