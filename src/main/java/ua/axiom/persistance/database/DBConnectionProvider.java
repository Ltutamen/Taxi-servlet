package ua.axiom.persistance.database;

import java.sql.Connection;

public abstract class DBConnectionProvider {
    public abstract Connection getConnection();

    public abstract void returnConnection(Connection connection);
}
