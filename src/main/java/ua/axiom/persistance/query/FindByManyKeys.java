package ua.axiom.persistance.query;

import ua.axiom.persistance.Fabricable;
import ua.axiom.persistance.database.DBConnectionProvider;

import java.util.List;

public class FindByManyKeys<K extends List<String>, T> extends OutQuery<K, T> {
    private final List<String> keyFields;


    public FindByManyKeys(Fabricable<T> factory, String table, List<String> keyFields, DBConnectionProvider provider) {
        super(factory, table, provider);
        this.keyFields = keyFields;
    }

    @Override
    protected String constructQueryString(K keyValues) {
        if(keyValues.size() != keyFields.size()) {
            throw new IllegalArgumentException("There are " + keyValues.size() + " key values, that is different from " + keyFields.size() + " number of key fields");
        }

        StringBuilder builder = new StringBuilder("SELECT * FROM " + table + " WHERE ");


        for(int i = 0; i < keyValues.size(); i++ ) {
            builder
                    .append(keyFields.get(i))
                    .append(" = ")
                    .append(toSqlParamString(keyValues.get(i)))
                    .append(" AND ");
        }
        builder.append(" TRUE; ");

        return builder.toString();
    }

    private String toSqlParamString(Object object) {
        if(object instanceof CharSequence) {
            return "'" + object.toString() + "'";
        }

        return object.toString();
    }
}
