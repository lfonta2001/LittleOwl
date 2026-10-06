package com.lfonta.littleowl.persistence.connection;

import com.lfonta.littleowl.persistence.config.DatabaseConfig;
import com.lfonta.littleowl.persistence.config.DatabaseProperties;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable {

    private final HikariDataSource dataSource;

    public DatabaseConnection(DatabaseProperties properties) {
        this.dataSource = DatabaseConfig.createDataSource(properties);
    }

    //public Connection getConnection() throws SQLException {
    //    return dataSource.getConnection();
    //}

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void close() {
        dataSource.close();
    }
}
