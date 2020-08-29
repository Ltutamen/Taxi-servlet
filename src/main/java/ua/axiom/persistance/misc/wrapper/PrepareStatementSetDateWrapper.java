package ua.axiom.persistance.misc.wrapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PrepareStatementSetDateWrapper extends PreparedStatementSetValueWrapper<Date> {

    public PrepareStatementSetDateWrapper(PreparedStatement statement, int pos) {
        super(statement, pos);
    }

    @Override
    public void consume(Date obj) throws SQLException {
        statement.setDate(pos, obj);
    }
}

