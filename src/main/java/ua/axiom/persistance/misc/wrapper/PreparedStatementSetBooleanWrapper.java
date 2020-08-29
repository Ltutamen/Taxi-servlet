package ua.axiom.persistance.misc.wrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementSetBooleanWrapper extends PreparedStatementSetValueWrapper<Boolean> {

    public PreparedStatementSetBooleanWrapper(PreparedStatement statement, int pos) {
        super(statement, pos);
    }

    @Override
    public void consume(Boolean obj) throws SQLException {
        statement.setBoolean(pos, obj);
    }
}
