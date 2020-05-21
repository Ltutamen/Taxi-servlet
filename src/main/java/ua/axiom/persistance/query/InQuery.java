package ua.axiom.persistance.query;

import ua.axiom.persistance.Fabricable;
import ua.axiom.persistance.Persistent;
import ua.axiom.persistance.database.DBConnectionProvider;

public abstract class InQuery<K, T extends Persistent<K> & Fabricable<T>> extends Query<K, T> {
    public InQuery(Class<T> exactClass, String table, DBConnectionProvider provider) {
        super(table, provider);
    }

    public abstract void execute(T object);

    //  protected abstract

}
