package ua.axiom.persistance.jdbcbased.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.persistance.dao.CRUDRepository;
import ua.axiom.persistance.jdbcbased.Persistent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//  todo use joins!!!
public abstract class MultiTableRepository<K, T extends Persistent<K>> {
    private List<CRUDRepository<K,  ? extends T>> repositories = new ArrayList<>();

    protected void addRepository(CRUDRepository<K, ? extends T> repository) {
        repositories.add(repository);
    }

    public List<T> findOne(final K id) {
        return repositories
                .stream()
                .map(ar -> ar.read(id))
                .collect(Collectors.toList());
    }

    public List<T> findByFields(Map<String, Object> keyValueMap) {
        return repositories
                .stream()
                .map(r ->r.findByFields(keyValueMap))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }



    public void save(T object, K key) {
        AbstractRepository<K, T> repository
                = (AbstractRepository<K, T>) repositories
                .stream()
                .reduce(
                        null,
                        (r, acc) -> {
                            if(r.getPersistedClass().equals(object.getClass())) {
                                return r;
                            }
                            return acc;});

        repository.save(object);

    }

    public void delete(K id) {
        throw new NotImplementedException();
    }

    public List<T> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }


}
