package ua.axiom.persistance.misc.wrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Abstract class, made to use different "set" methods in PreparedStatement class
 * @param <T>
 */
public abstract class PreparedStatementSetValueWrapper<T> {
    protected final PreparedStatement statement;
    protected final int pos;

    public PreparedStatementSetValueWrapper(PreparedStatement statement, int pos) {
        this.statement = statement;
        this.pos = pos;
    }

    public abstract void consume(T obj) throws SQLException ;
}
