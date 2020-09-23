package ua.axiom.persistance.jdbcbased.database;

import org.apache.commons.dbcp.BasicDataSource;
import javax.sql.DataSource;

import ua.axiom.core.annotations.Component;
import ua.axiom.persistance.jdbcbased.JDBCConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Component
public class SimpleDBConnectionProvider extends DBConnectionProvider {
    private static volatile DataSource dataSource;

    private final Stack<Connection> connections = new Stack<>();

    private final Set<Connection> userConnections = Collections.synchronizedSet(new HashSet<>());

    @Override
    public Connection getConnection() {

        if (dataSource == null) {
            synchronized (SimpleDBConnectionProvider.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(JDBCConfiguration.DB_URL);
                    ds.setUsername(JDBCConfiguration.USER);
                    ds.setPassword(JDBCConfiguration.PASSWORD);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(10);
                    dataSource = ds;
                }
            }
        }

        synchronized (SimpleDBConnectionProvider.class) {
            try {
                if(connections.empty()) {
                    connections.push(dataSource.getConnection());
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            Connection connectionToReturn = connections.pop();
            userConnections.add(connectionToReturn);

            return connectionToReturn;
        }
    }

    @Override
    public void returnConnection(Connection connection) {
        synchronized (SimpleDBConnectionProvider.class) {
            userConnections.remove(connection);
            connections.push(connection);
        }
    }
}
