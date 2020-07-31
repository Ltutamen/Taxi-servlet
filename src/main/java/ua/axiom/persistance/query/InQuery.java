package ua.axiom.persistance.query;

import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.database.DBConnectionProvider;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class InQuery<K, T extends Persistent<K>> extends Query<K, T> {
    private String[] fields;
    private Map<Class<?>, List<Field>> cachedFields = new HashMap<>();
    private String statement = null;

    public InQuery(String table, String[] fields, DBConnectionProvider provider) {
        super(table, provider);
        this.fields = fields;
    }

    //  todo refactor
    public void execute(T object, K key) {
        //  todo execute  update on key
        String sStatement = getStatement();

        PreparedStatement statement = null;
        try {
            statement = provider.getConnection().getConnection().prepareStatement(sStatement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        Field[] objectFields = object.getClass().getFields();
        try {
            for(int i = 0; i < object.getFieldsNum(); i++) {
                statement.setObject(i, objectFields[i].get(object));
            }
        } catch (IllegalAccessException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        try {

            for(int i = 1; i <= fields.length; ++i) {
                Field field = object.getClass().getDeclaredField(fields[i-1]);
                field.setAccessible(true);
                statement.setObject(i, field.get(object));
            }

            statement.execute();

        } catch (SQLException | NoSuchFieldException | IllegalAccessException sqlException) {
            throw new RuntimeException(sqlException.getMessage());
        }

    }

    protected String getStatement() {
        if(statement != null) {
            return statement;
        }

        StringBuilder newStatement = new StringBuilder();
        newStatement.append("INSERT INTO " + table + " ( ");
        for(String s : fields) {
            newStatement.append(" ").append(s).append(", ");
        }
        newStatement.setCharAt(newStatement.length() - 2, ')');
        newStatement.append(" VALUES (");

        for(String s : fields) {
            newStatement.append("?,");
        }

        newStatement.setCharAt(newStatement.length() - 1, ')');
        newStatement.append(';');

        statement = newStatement.toString();
        return statement;
    }

    protected List<Field> getAllClassFields(Class<?> c) {
        if(cachedFields.containsKey(c)) {
            return cachedFields.get(c);
        }

        List<Field> fields = new ArrayList<>();
        while (c != Object.class) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
            c = c.getSuperclass();
        }

        cachedFields.put(c, fields);
        return fields;
    }



}
