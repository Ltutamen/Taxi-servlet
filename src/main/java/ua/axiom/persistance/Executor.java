package ua.axiom.persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Executor {

    public static <T> List<T> getResult(ResultSet resultSet, Fabricable<T> factory) {
        int columnCount = 0;
        try {
            columnCount = resultSet.getMetaData().getColumnCount() + 1;

            List<T> resultList = new ArrayList<>();

            while (resultSet.next()) {
                String[] row = new String[columnCount];
                for (int i = 1; i < columnCount; i++) {
                    row[i] = resultSet.getString(i);
                }
                resultList.add(factory.fabricate(row));
            }

            return resultList;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException(sqlException.getMessage());
        }
    }

    public static void setValues(PreparedStatement statement, List<String> values) {

    }
}
