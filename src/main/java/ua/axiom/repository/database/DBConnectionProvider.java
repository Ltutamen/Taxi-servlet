package ua.axiom.repository.database;

import javax.sql.DataSource;

public abstract class DBConnectionProvider {
    public abstract DataSource getConnection();

    public abstract void returnConnection(DataSource connection);
}
