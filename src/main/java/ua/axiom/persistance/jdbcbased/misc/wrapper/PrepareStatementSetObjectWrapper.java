package ua.axiom.persistance.jdbcbased.misc.wrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepareStatementSetObjectWrapper extends PreparedStatementSetValueWrapper<Object> {
    public PrepareStatementSetObjectWrapper(PreparedStatement statement, int pos) {
        super(statement, pos);
    }

    @Override
    public void consume(Object obj) throws SQLException {
        statement.setObject(pos, obj);
    }
}
