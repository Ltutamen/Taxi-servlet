package ua.axiom.repository.query;

import ua.axiom.repository.Fabricable;
import ua.axiom.repository.Persistent;
import ua.axiom.repository.database.DBConnectionProvider;

public class FindAllQuery<K, T extends Persistent<K>> extends OutQuery<K, T> {

    public FindAllQuery(Fabricable<T> factory, String table, DBConnectionProvider provider) {
        super(factory, table, provider);
    }

    @Override
    protected String constructQueryString(K key) {
        return "SELECT * FROM " + table;
    }
}
