package ua.axiom.persistance.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.core.annotations.InitMethod;
import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.query.InQuery;
import ua.axiom.persistance.repository.AbstractRepository;
import ua.axiom.persistance.repository.impl.AdminRepository;
import ua.axiom.persistance.repository.impl.ClientRepository;
import ua.axiom.persistance.repository.impl.DriverRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//  todo use joins!!!
public abstract class MultiTableRepository<K, T extends Persistent<K>> {
    private List<AbstractRepository<K,  ? extends T>> repositories = new ArrayList<>();

    protected void addRepository(AbstractRepository<K, ? extends T> repository) {
        repositories.add(repository);
    }

    public List<T> findAll() {
        return repositories
                .stream()
                .map(AbstractRepository::findAll)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<T> findOne(final K id) {
        return repositories
                .stream()
                .map(ar -> ar.findOne(id))
                .flatMap(List::stream)
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
