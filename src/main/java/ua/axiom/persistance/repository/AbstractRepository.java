package ua.axiom.persistance.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.query.*;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Abstract repository, that supports basic CRUD operations with a database over a specific table
 * @param <K> key of the T
 * @param <T> table entity type
 */
//  todo remove field to T name mapping, use field name instead
public abstract class AbstractRepository<K, T extends Persistent<K>> {
    private final OutQuery<K, T> findQuery;
    private final OutQuery<K, T> selectQuery;

    private final InQuery<K, T> saveQuery;
    private final UpdateQuery<K, T> updateQuery;
    //  maps field names to set of queries, that take unknown Key and produce T
    private final Map<List<String>, OutQuery<List<String>, T>> findByFieldMapping = new HashMap<>();

    protected AbstractRepository(
            OutQuery<K, T> findQuery,
            OutQuery<K, T> selectQuery,
            InQuery<K, T> saveOneQuery,
            UpdateQuery<K, T> updateQuery,
            Map.Entry<List<String>, OutQuery<List<String>, T>> ... byFieldsQuery
    ) {
        this.findQuery = findQuery;
        this.selectQuery = selectQuery;
        this.saveQuery = saveOneQuery;
        this.updateQuery = updateQuery;
        findByFieldMapping.putAll(
                Arrays
                        .stream(byFieldsQuery)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }


    public List<T> findAll() {
        return findQuery.execute(null);
    }

    public List<T> findOne(K id) {
        return selectQuery.execute(id);
    }

    public List<T> findByKey(String keyName, String keyValue) {
        throw new NotImplementedException();
    }

    public void save(T object) {
        saveQuery.execute(object, object.getId());
    }

    public void update(T object, Field[] fields) {
        updateQuery.execute(object, object.getId(), fields);
    }

    public void delete(Long id) {
        throw new NotImplementedException();
    }

    public List<T> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }

    /**
     * Finds all rows, where
     * @param fieldNames equals to the
     * @param keys, that represents field value
     */
    public List<T> findByFields(List<String> fieldNames, List<String> keys) {
        if(findByFieldMapping.containsKey(fieldNames)) {
            return findByFieldMapping.get(fieldNames).execute(keys);
        } else {
            throw new IllegalArgumentException("No query for field <" + fieldNames + "> for repository " + this.getClass());
        }
    }

    public abstract Class<T> getPersistedClass();

}
