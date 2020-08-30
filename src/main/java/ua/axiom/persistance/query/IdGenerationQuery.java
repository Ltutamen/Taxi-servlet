package ua.axiom.persistance.query;

import ua.axiom.persistance.database.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IdGenerationQuery {
    private final DBConnectionProvider provider;

    public IdGenerationQuery(DBConnectionProvider provider) {
        this.provider = provider;
    }

    public Long execute() {
        try (
                Connection connection = provider.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(constructQueryString())
        ) {

            resultSet.next();
            return resultSet.getLong(1);

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        }
    }

    protected String constructQueryString() {
        return "select nextval(hibernate_sequence)";
    }

}
