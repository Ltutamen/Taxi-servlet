package ua.axiom.persistance.jdbcbased.query;

import ua.axiom.persistance.jdbcbased.Fabric;
import ua.axiom.persistance.jdbcbased.Persistent;
import ua.axiom.persistance.jdbcbased.database.DBConnectionProvider;

public class FindAllQuery<K, T extends Persistent<K>> extends OutQuery<K, T> {

    public FindAllQuery(Fabric<T> factory, String table, DBConnectionProvider provider) {
        super(factory, table, provider);
    }

    @Override
    protected String constructQueryString(K key) {
        return "SELECT * FROM " + table;
    }
}
