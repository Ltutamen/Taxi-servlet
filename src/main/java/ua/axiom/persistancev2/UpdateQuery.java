package ua.axiom.persistancev2;

import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.database.DBConnectionProvider;

import java.util.Map;
import java.util.Set;

/*
public class UpdateQuery<K, T extends Persistent<K>> {
    private final String preparedStatementString;
    private final Set<String> keys;
    private final DBConnectionProvider connectionProvider;

    public UpdateQuery(Set<String> keys, String tableName, DBConnectionProvider connectionProvider) {
        this.keys = keys;
        this.preparedStatementString = getPreparedStatementString(tableName, keys);
        this.connectionProvider = connectionProvider;
    }


    public void execute(T object) {

    }

    private static String getPreparedStatementString(String tableName, Set<String> keys) {
        StringBuilder builder = new StringBuilder();

        builder.append("UPDATE ").append(tableName).append(" SET ");

        for (String key : keys) {
            builder.append(key).append(" = ?, ");
        }

        builder.append(" AND TRUE WHERE");


    }
}
*/
