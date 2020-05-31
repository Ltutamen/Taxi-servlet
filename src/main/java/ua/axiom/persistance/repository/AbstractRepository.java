package ua.axiom.persistance.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.persistance.query.OutQuery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractRepository<K, T> {
    private final OutQuery<K, T> findQuery;
    private final OutQuery<K, T> selectQuery;
    //  maps field names to set of queries, that take unknown Key and produce T
    private final Map<String, OutQuery<String, T>> findByFieldMapping = new HashMap<>();

    protected AbstractRepository(
            OutQuery<K, T> findQuery,
            OutQuery<K, T> selectQuery,
            Map.Entry<String, OutQuery<String, T>> ... byFieldsQuery
    ) {
        this.findQuery = findQuery;
        this.selectQuery = selectQuery;
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

    //  todo pass T here
    public void save(Object object) {
        throw new NotImplementedException();
    }

    public abstract Class<T> getPersistedClass();

    public void delete(Long id) {
        throw new NotImplementedException();
    }

    public List<T> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }

    /**
     * Finds all rows, where
     * @param fieldName equals to the
     * @param key, that represents field value
     */
    public List<T> findByField(String fieldName, String key) {
        if(findByFieldMapping.containsKey(fieldName)) {
            return findByFieldMapping.get(fieldName).execute(key);
        } else {
            throw new IllegalArgumentException("No query for field <" + fieldName + ">");
        }
    }

}
