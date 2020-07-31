package ua.axiom.persistance.query;

import ua.axiom.core.Context;
import ua.axiom.persistance.database.SimpleDBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class IdGenerationQuery extends OutQuery<Void, Long> {
    public IdGenerationQuery() {
        super(
                (String[] args) -> Long.parseLong(args[0]),
                "",
                Context.get(SimpleDBConnectionProvider.class)
        );
    }

    @Override
    public List<Long> execute(Void key) {
        try (
                Connection connection = provider.getConnection().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(constructQueryString(key))
        ) {

            resultSet.next();
            return Collections.singletonList(resultSet.getLong(1));

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle.getMessage());
        }
    }

    @Override
    protected String constructQueryString(Void key) {
        return "select nextval(hibernate_sequence)";
    }

}
