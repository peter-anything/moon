package com.peter.moon.d2r.flows;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class DBRow implements Row {
    private ResultSet resultSet;
    private int columnLen = 0;

    public DBRow(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        this.columnLen = resultSet.getMetaData().getColumnCount();
    }

    public boolean hasNext() {
        try {
            return this.resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public RowData next() {
//        if (!hasNext()) throw new NoSuchElementException();
        String[] result = new String[this.columnLen];
        for (int i = 0; i < result.length; i++) {
            try {
                result[i] = resultSet.getString(i + 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new RowData() {
            @Override
            public Object getData() {
                return result;
            }
        };
    }

    @Override
    public Iterator<RowData> iterator() {
        return this;
    }
}
