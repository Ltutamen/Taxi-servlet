package ua.axiom.persistance.jdbcbased.query;

import ua.axiom.persistance.jdbcbased.Persistent;
import ua.axiom.persistance.jdbcbased.PersistentFieldUtil;
import ua.axiom.persistance.jdbcbased.database.DBConnectionProvider;
import ua.axiom.persistance.jdbcbased.misc.representation.persision.GeneralPersisting;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static ua.axiom.persistance.jdbcbased.misc.JavaSqlTypeGetter.getJavaSqlType;

public class UpdateQuery<K, T extends Persistent<K>> extends InQuery<K, T> {
    //  private final String preparedStatementString;
    private final Map<Field[], String> fieldsToQueryStringMap;
    private final Field idField;
    private final String tableName;

    public UpdateQuery(String table, String idFieldName, Class<T> tClass, DBConnectionProvider provider) {
        super(table, provider);
        Field[] objectFields = PersistentFieldUtil.getAllFieldsAndSetAccessible(tClass);
        fieldsToQueryStringMap = new HashMap<>();
        this.tableName = table;

        this.idField = Arrays
                .stream(objectFields)
                .filter(field -> field.getName().equals(idFieldName))
                .findAny().get();

        idField.setAccessible(true);

    }

    public void execute(T object, K key, Field[] fieldsToUpdate) {
        if(!fieldsToQueryStringMap.containsKey(fieldsToUpdate)) {
            fieldsToQueryStringMap.put(fieldsToUpdate, getPreparedStatementString(fieldsToUpdate, tableName, idField));
        }

        PreparedStatement statement = null;
        try {
            String preparedStatementString = fieldsToQueryStringMap.get(fieldsToUpdate);
            statement = provider.getConnection().prepareStatement(preparedStatementString);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        try {
            int i = 0;

            //  set non-key values
            for(Field field : fieldsToUpdate) {
                if(field.getName().equals(idField.getName())) {
                    break;
                }

                field.setAccessible(true);

                //  todo refactor
                if(field.getType().isAssignableFrom(boolean.class)) {
                    statement.setBoolean(i+1, GeneralPersisting.getBoolRepresentation(field, field.get(object)));
                } else if(field.get(object) == null) {
                    statement.setNull(i+1, getJavaSqlType(field.getType()));
                } else {
                    statement.setObject(i+1, GeneralPersisting.getRepresentation(field, field.get(object)));
                }
                i++;
            }

            //  set key value
            statement.setObject(i+1, key);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }

        try {
            statement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private static String getPreparedStatementString(Field[] fields, String tableName, Field idField) {

        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ").append(tableName).append(" SET ");

        for (Field field : fields) {
            if(field.equals(idField)) {
                throw new RuntimeException("Cannot update id field in table <" + tableName + ">");
            }

            builder.append(field.getName()).append(" = ?, ");
        }

        //  remove last coma
        builder.delete(builder.length()-2, builder.length()-1);
        builder
                .append(" WHERE ")
                .append(idField.getName())
                .append(" = ? ;");

        return builder.toString();
    }

}
