package ua.axiom.persistance.repository.impl;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.query.InQuery;
import ua.axiom.persistance.repository.AbstractRepository;

import java.util.List;
import java.util.stream.Collectors;

//  todo use joins!!!
public class MultiTableRepository<K, T extends Persistent<K>> {

    List<AbstractRepository<K,  T>> repositories;

    public MultiTableRepository(List<AbstractRepository<K,  T>> repositories) {
        this.repositories = repositories;
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

    public List<T> findByFields(final List<String> fields, final List<String> keys) {
        return repositories
                .stream()
                .map(r ->r.findByFields(fields, keys))
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
