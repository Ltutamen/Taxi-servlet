package ua.axiom.model.repository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.factories.ClientFactory;
import ua.axiom.repository.AbstractRepository;
import ua.axiom.repository.database.SimpleDBConnectionProvider;
import ua.axiom.repository.query.FindAllQuery;
import ua.axiom.repository.query.FindOneQuery;
import ua.axiom.repository.query.OutQuery;

import java.util.List;

public class ClientRepository extends AbstractRepository<Long, Client> {
    //  todo make in a factory and Autowire
    private final OutQuery<Long, Client> findQuery = new FindAllQuery<>(new ClientFactory(), "clients", new SimpleDBConnectionProvider());
    private final OutQuery<Long, Client> selectQuery = new FindOneQuery<>(new ClientFactory(), "clients", "id", new SimpleDBConnectionProvider());
    private final OutQuery<String, Client> selectUsernameQuery = new FindOneQuery<>(new ClientFactory(), "clients", "username", new SimpleDBConnectionProvider());
    //  todo private Query<Long, Client> saveQuery = new <>(Client)

    public ClientRepository() { }

    @Override
    public List<Client> findAll() {
        return findQuery.execute(null);
    }

    @Override
    public List<Client> findOne(Long id) {
        return selectQuery.execute(id);
    }

    public List<Client> findByUsername(String username) {
        return selectUsernameQuery.execute(username);
    }

    @Override
    public void save(Client object) {
        throw new NotImplementedException();

    }

    @Override
    public void delete(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public List<Client> findAll(int page, int onPage) {
        throw new NotImplementedException();
    }
}
