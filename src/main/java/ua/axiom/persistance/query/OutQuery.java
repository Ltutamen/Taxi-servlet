package ua.axiom.persistance.query;

import ua.axiom.persistance.Executor;
import ua.axiom.persistance.Fabric;
import ua.axiom.persistance.database.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class OutQuery<K, T> extends Query<K, T> {
    protected Fabric<T> objectFactory;

    public OutQuery(Fabric<T> factory, String table, DBConnectionProvider provider) {
        super(table, provider);
        objectFactory = factory;

    }

    public List<T> execute(K key) {
        List<T> result;

        try (
                Connection connection = provider.getConnection().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(constructQueryString(key))
        ) {
            result = Executor.getResult(resultSet, objectFactory);
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return result;
    }

    protected abstract String constructQueryString(K key);
}
