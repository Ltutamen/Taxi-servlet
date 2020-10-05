package ua.axiom.persistance.jdbcbased.query;

import ua.axiom.persistance.jdbcbased.Executor;
import ua.axiom.persistance.jdbcbased.Fabric;
import ua.axiom.persistance.jdbcbased.Persistent;
import ua.axiom.persistance.jdbcbased.database.DBConnectionProvider;
import ua.axiom.persistance.jdbcbased.misc.representation.persision.GeneralPersisting;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ua.axiom.persistance.jdbcbased.PersistentFieldUtil.getAllFieldsAndSetAccessible;

public class FindByKeysQuery<K, T extends Persistent<K>> extends Query<T, K> {
    private final Map<Set<String>, String> keySetToQueryStringMap;
    private final Fabric<T> objectFactory;

    public FindByKeysQuery(Fabric<T> factory, String tableName, DBConnectionProvider provider) {
        super(tableName, provider);
        this.keySetToQueryStringMap = new HashMap<>();
        this.objectFactory = factory;
    }

    public List<T> execute(Class<T> objectClass, Map<String, Object> keyValueFieldMap) {
        if(!keySetToQueryStringMap.containsKey(keyValueFieldMap.keySet())) {
            keySetToQueryStringMap.put(keyValueFieldMap.keySet(), getQueryString(keyValueFieldMap, table));
        }

        String query = keySetToQueryStringMap.get(keyValueFieldMap.keySet());
        Field[] fields = getAllFieldsAndSetAccessible(objectClass);

        try (
                Connection connection = provider.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            {
                Iterator<String> keyi = keyValueFieldMap.keySet().iterator();
                int i = 1;
                while (keyi.hasNext()) {
                    String key = keyi.next();

                    Object value = keyValueFieldMap.get(key);
                    Field field = findFieldByName(fields, key);

                    statement.setObject(i, GeneralPersisting.getRepresentation(field, value));

                    i++;
                }
            }

            ResultSet resultSet = statement.executeQuery();

            //  todo move out object creation
            return Executor.getResult(resultSet, objectFactory);
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
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