package ua.axiom.persistance.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.actors.Client;
import ua.axiom.persistance.query.OutQuery;

import java.util.List;

public abstract class AbstractRepository<K, T> {
    private final OutQuery<K, T> findQuery;
    private final OutQuery<K, T> selectQuery;
    private final OutQuery<String, T> selectByUsernameQuery;

    protected AbstractRepository(
            OutQuery<K, T> findQuery,
            OutQuery<K, T> selectQuery,
            OutQuery<String, T> selectByUsernameQuery
    ) {
        this.findQuery = findQuery;
        this.selectQuery = selectQuery;
        this.selectByUsernameQuery = selectByUsernameQuery;
    }

    public List<T> findAll() {
        return findQuery.execute(null);
    }

    public List<T> findOne(K id) {
        return selectQuery.execute(id);
    }

    /**
     * Class "AbstractRepository can be user to serve entities without usernames,
     * but to do a shortcut, I place this method here"
     * @param username
     * @return
     */
    //  todo remove
    public List<T> findByUsername(String username) {
        return selectByUsernameQuery.execute(username);
    }

    //  todo pass T here
    public void save(Object object) {
        throw new NotImplementedException();
    }

    public abstract Class<T> getPersistedClass();

    public void delete(Long id) {
        throw new NotImplementedException();
    }

    public List<Client> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }

}
