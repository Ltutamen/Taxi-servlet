package ua.axiom.repository.query;

import ua.axiom.repository.Fabricable;
import ua.axiom.repository.Persistent;
import ua.axiom.repository.database.DBConnectionProvider;

public abstract class InQuery<K, T extends Persistent<K> & Fabricable<T>> extends Query<K, T> {
    public InQuery(Class<T> exactClass, String table, DBConnectionProvider provider) {
        super(table, provider);
    }

    public abstract void execute(T object);

    //  protected abstract

}
