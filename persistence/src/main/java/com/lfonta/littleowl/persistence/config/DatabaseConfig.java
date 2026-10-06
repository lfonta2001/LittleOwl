package com.lfonta.littleowl.persistence.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {

    public static HikariDataSource createDataSource(
            DatabaseProperties properties
    ) {
        String url =
                String.format(
                        "jdbc:postgresql://%s:%d/%s",
                        properties.host(),
                        properties.port(),
                        properties.database()
                );

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(properties.username());
        config.setPassword(properties.password());

        return new HikariDataSource(config);
    }
}
