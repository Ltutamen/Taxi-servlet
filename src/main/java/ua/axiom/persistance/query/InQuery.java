package ua.axiom.persistance.query;

import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.database.DBConnectionProvider;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InQuery<K, T extends Persistent<K>> extends Query<K, T> {
    private String statement = null;

    public InQuery(String table, DBConnectionProvider provider) {
        super(table, provider);
    }

    //  todo refactor
    public void execute(T object, K key) {
        //  todo execute  update on key
        String sStatement = getStatement(object);

        PreparedStatement statement = null;
        try {
            statement = provider.getConnection().prepareStatement(sStatement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        try {
            Field[] objectFields = object.getOrderedFields();

            for(int i = 1; i <= objectFields.length ; ++i) {
                Field field = objectFields[i-1];
                field.setAccessible(true);
                //  todo persistent strategies
                if(field.getType().isEnum()) {
                    statement.setObject(i, ((Enum)field.get(object)).ordinal());
                }
                else
                    statement.setObject(i, field.get(object));
            }

            statement.execute();

        } catch (SQLException | IllegalAccessException sqlException) {
            throw new RuntimeException(sqlException.getMessage());
        }
    }

    protected String getStatement(Persistent<K> object) {
        if(statement != null) {
            return statement;
        }

        Field[] fields = object.getOrderedFields();

        StringBuilder newStatement = new StringBuilder();
        newStatement.append("INSERT INTO ").append(table).append(" ( ");
        for(Field field : fields) {
            newStatement.append(" ").append(field.getName()).append(", ");
        }
        newStatement.setCharAt(newStatement.length() - 2, ')');
        newStatement.append(" VALUES (");

        for(Field field : fields) {
            newStatement.append("?,");
        }

        newStatement.setCharAt(newStatement.length() - 1, ')');
        newStatement.append(';');

        statement = newStatement.toString();
        return statement;
    }
}
