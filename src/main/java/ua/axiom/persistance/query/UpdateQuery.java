package ua.axiom.persistance.query;

import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.PersistentFieldUtil;
import ua.axiom.persistance.database.DBConnectionProvider;
import ua.axiom.persistance.misc.representation.GeneralPersisting;
import ua.axiom.persistance.misc.representation.PersistingDepersistingStrategyProvider;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
            statement = provider.getConnection().getConnection().prepareStatement(preparedStatementString);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        try {
            int i = 0;
            for(Field field : fieldsToUpdate) {
                if(field.getName().equals(idField.getName())) {
                    break;
                }

                field.setAccessible(true);
                statement.setObject(i+1, GeneralPersisting.getRepresentation(field, field.get(object)));
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
