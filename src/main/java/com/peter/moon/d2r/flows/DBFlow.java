package com.peter.moon.d2r.flows;

import com.peter.moon.factory.SqlConnectionFactory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class DBFlow implements Flow, Iterable<String[]> {
    private static final Logger logger = LoggerFactory.getLogger(SqlConnectionFactory.class);
    String sql = "select * from auth_user";

    @Override
    public Object process() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = SqlConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
//            DBUtil.closeQuietly(resultSet);
//            DBUtil.closeQuietly(statement);
//            DBUtil.closeQuietly(connection);
        }
        return null;
    }

    @NotNull
    @Override
    public Iterator<String[]> iterator() {
        return null;
    }
}
