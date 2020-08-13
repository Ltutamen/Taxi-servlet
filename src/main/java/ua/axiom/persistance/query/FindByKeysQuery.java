package ua.axiom.persistance.query;

import com.google.common.collect.Sets;
import ua.axiom.persistance.Fabricable;
import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.database.DBConnectionProvider;
import ua.axiom.persistance.misc.representation.GeneralPersisting;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ua.axiom.persistance.PersistentFieldUtil.getAllFieldsAndSetAccessible;

public class FindByKeysQuery<K, T extends Persistent<K>> {
    private final Map<Set<String>, String> keySetToQueryStringMap;

    private final String tableName;
    private final DBConnectionProvider provider;
    private final Fabricable<T> objectFactory;

    public FindByKeysQuery(Fabricable<T> factory, String tableName, DBConnectionProvider provider) {
        this.tableName = tableName;
        this.keySetToQueryStringMap = new HashMap<>();
        this.provider = provider;
        this.objectFactory = factory;
    }

    public List<T> execute(Class<T> object, Map<String, Object> keyValueFieldMap) {
        if(!keySetToQueryStringMap.containsKey(keyValueFieldMap.keySet())) {
            keySetToQueryStringMap.put(keyValueFieldMap.keySet(), getQueryString(keyValueFieldMap, tableName));
        }

        String query = keySetToQueryStringMap.get(keyValueFieldMap.keySet());
        List<T> result = new ArrayList<>();
        Field[] fields = getAllFieldsAndSetAccessible(object);

        try (
                Connection connection = provider.getConnection().getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            {
                Iterator<String> keyi = keyValueFieldMap.keySet().iterator();
                int i = 1;
                while (keyi.hasNext()) {
                    //  todo unse object as a value, and check for Enum to insert number
                    String key = keyi.next();

                    Object value = keyValueFieldMap.get(key);
                    Field field = findFieldByName(fields, key);
                    statement.setObject(i, GeneralPersisting.getRepresentation(field, value));

                    /*//  todo remove
                    if(object.getClass().isEnum()) {

                        statement.setObject(i, ((Enum)object).ordinal());
                    }
                    else {
                        statement.setObject(i, object.toString());
                    }*/
                    i++;
                }
            }

            ResultSet resultSet = statement.executeQuery();

            //  todo move out object creation
            int columnCount = resultSet.getMetaData().getColumnCount() + 1;
            while (resultSet.next()) {
                String[] row = new String[columnCount];
                for (int i = 1; i < columnCount; i++) {
                    row[i] = resultSet.getString(i);
                }
                result.add(objectFactory.fabricate(row));
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return result;
    }

    private static String getQueryString(Map<String, Object> keyValueFieldMap, String tableName) {
        StringBuilder builder = new StringBuilder();

        builder
                .append("SELECT * FROM ")
                .append(tableName)
                .append(" WHERE ");

        for (String s : keyValueFieldMap.keySet()) {
            builder.append(s).append(" = ? AND ");
        }

        builder.setLength(builder.length() - 5);

        builder.append(";");

        return builder.toString();
    }

    private static Field findFieldByName(Field[] fields, String name) {
        for(Field field : fields) {
            if(field.getName().equals(name)) {
                return field;
            }
        }
        throw new IllegalStateException("Field[] should contain needed field");
    }
}
