package ua.axiom.repository.query;

import ua.axiom.repository.Fabricable;
import ua.axiom.repository.database.DBConnectionProvider;

/**
 * @param <K> key in the table
 * @param <T> type of object to return
 */
public abstract class Query<K, T> {
    protected final DBConnectionProvider provider;
    protected final String table;

    public Query(String table, DBConnectionProvider provider) {
        this.table = table;
        this.provider = provider;
    }




}
