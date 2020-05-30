package ua.axiom.persistance.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;

public class MultiTableRepository<K, T> {

    List<AbstractRepository<K,  ? extends T>> repositories;

    public MultiTableRepository(List<AbstractRepository<K,  ? extends T>> repositories) {
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

    public List<T> findByUsername(String username) {
        return repositories
                .stream()
                .map(r ->r.findByUsername(username))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public void save(T object) {
        repositories
                .stream()
                .reduce(
                        null,
                        (r, acc) -> {
                            if(r.getPersistedClass().equals(object.getClass())) {
                                return r;
                            }
                            return acc;})
                .save(object);
    }

    public void delete(K id) {
        throw new NotImplementedException();
    }

    public List<T> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }


}
