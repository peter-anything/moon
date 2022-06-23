package com.peter.moon.factory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(SqlConnectionFactory.class);

    public static Connection getConnection() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setUsername("root");
        config.setPassword("root");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://10.70.12.67:3308/kg_db?characterEncoding=utf-8&useSSL=false");
        config.setMaximumPoolSize(24);
        config.setAutoCommit(true);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource.getConnection();
    }
}
