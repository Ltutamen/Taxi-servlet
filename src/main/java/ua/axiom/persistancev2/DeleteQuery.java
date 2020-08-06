package ua.axiom.persistancev2;

import ua.axiom.persistance.database.DBConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DeleteQuery {
    private final String preparedStatementString;
    private final Set<String> keys;
    private final DBConnectionProvider connectionProvider;

    public DeleteQuery(Set<String> keys, String tableName, DBConnectionProvider connectionProvider) {
        this.keys = keys;
        this.preparedStatementString = getPreparedStatementString(tableName, keys);
        this.connectionProvider = connectionProvider;
    }

    public void execute(Map<String, String> keysToValuesMap) {
        try (
            PreparedStatement statement = connectionProvider.getConnection().getConnection().prepareStatement(preparedStatementString)
        ) {
            int i = 0;
            Iterator<String> keyi = keys.iterator();

            while (i<keys.size()) {

                statement.setObject(i, keyi.next());

                i++;
            }

            statement.execute();

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException.getMessage());
        }
    }


    private static String getPreparedStatementString(String tableMame, Set<String> keyNames) {
        StringBuilder builder = new StringBuilder();
        builder.append("DELETE FROM ").append(tableMame).append(" WHERE ");

        for(String key : keyNames) {
            builder.append(key).append(" = ? ").append(" AND ");
        }

        builder.append(" TRUE;");

        return builder.toString();
    }
}
