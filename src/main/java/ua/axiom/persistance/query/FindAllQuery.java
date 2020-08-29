package ua.axiom.persistance.query;

import ua.axiom.persistance.Fabric;
import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.database.DBConnectionProvider;

public class FindAllQuery<K, T extends Persistent<K>> extends OutQuery<K, T> {

    public FindAllQuery(Fabric<T> factory, String table, DBConnectionProvider provider) {
        super(factory, table, provider);
    }

    @Override
    protected String constructQueryString(K key) {
        return "SELECT * FROM " + table;
    }
}
