package ua.axiom.persistance.database;

import org.apache.commons.dbcp.BasicDataSource;
import javax.sql.DataSource;
import ua.axiom.persistance.JDBCConfiguration;

public class SimpleDBConnectionProvider extends DBConnectionProvider {
    private static volatile DataSource dataSource;

    @Override
    public DataSource getConnection() {

        if (dataSource == null) {
            synchronized (SimpleDBConnectionProvider.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(JDBCConfiguration.DB_URL);
                    ds.setUsername(JDBCConfiguration.USER);
                    ds.setPassword(JDBCConfiguration.PASSWORD);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }

        return dataSource;
    }

    @Override
    public void returnConnection(DataSource connection) {

    }
}
