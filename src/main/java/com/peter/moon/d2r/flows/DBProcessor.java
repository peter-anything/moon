package com.peter.moon.d2r.flows;

import com.peter.moon.factory.SqlConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBProcessor implements DataSourceProcessor {
    private static final Logger logger = LoggerFactory.getLogger(SqlConnectionFactory.class);
    String sql = "select * from auth_user";

    public DBProcessor() {}

    @Override
    public Row process() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        connection = SqlConnectionFactory.getConnection();
        statement = connection.prepareStatement(sql);
        resultSet = statement.executeQuery();

        return new DBRow(resultSet);
    }
}
