package com.lfonta.littleowl.persistence.config;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class DatabaseMigrator {

    private static final String MIGRATIONS_LOCATIONS = "classpath:db/migration";

    private DatabaseMigrator(){}

    public static void migrate(DataSource dataSource) {
        Flyway.configure()
                .dataSource(dataSource)
                .locations(MIGRATIONS_LOCATIONS)
                .load()
                .migrate();
    }
}
