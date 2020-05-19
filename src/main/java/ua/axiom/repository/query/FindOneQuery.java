package ua.axiom.repository.query;

import ua.axiom.repository.Fabricable;
import ua.axiom.repository.database.DBConnectionProvider;

public class FindOneQuery<K, T> extends OutQuery<K, T> {
    private final String keyName;

    public FindOneQuery(Fabricable<T> factory, String table, String keyName, DBConnectionProvider provider) {
        super(factory, table, provider);
        this.keyName = keyName;
    }

    @Override
    protected String constructQueryString(K key) {
        return "SELECT * FROM " + table + " WHERE " + keyName + " = '" + key + "'";
    }
}
