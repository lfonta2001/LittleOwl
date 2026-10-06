package com.lfonta.littleowl.persistence;

import com.lfonta.littleowl.persistence.config.DatabaseMigrator;
import com.lfonta.littleowl.persistence.config.DatabaseProperties;
import com.lfonta.littleowl.persistence.connection.DatabaseConnection;
import com.lfonta.littleowl.persistence.repositories.interfaces.GuildRepository;
import com.lfonta.littleowl.persistence.repositories.interfaces.Repository;
import com.lfonta.littleowl.persistence.repositories.interfaces.UserRepository;
import com.lfonta.littleowl.persistence.repositories.jOOQUserRepository;
import com.lfonta.littleowl.persistence.repositories.jOOQGuildRepository;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.HashMap;
import java.util.Map;

public class PersistenceModule implements AutoCloseable {

    private final DSLContext dsl;

    private final DatabaseConnection connection;

    private final Map<Class<? extends Repository>, Repository> repositories = new HashMap<>();

    public PersistenceModule(DatabaseProperties properties) {
        connection = new DatabaseConnection(properties);

        try {
            DatabaseMigrator.migrate(connection.getDataSource());
        } catch (RuntimeException e) {
            connection.close();
            throw e;
        }

        this.dsl = DSL.using(connection.getDataSource(), SQLDialect.POSTGRES);
        this.repositories.put(GuildRepository.class, new jOOQGuildRepository(dsl));
        this.repositories.put(UserRepository.class, new jOOQUserRepository(dsl));
    }

    public <T extends Repository> T getRepository(Class<T> repoClass) {
        T repository = repoClass.cast(repositories.get(repoClass));
        if (repository == null) {
            throw new IllegalArgumentException("Repository not registered: " + repoClass.getSimpleName());
        }
        return repository;
    }

    @Override
    public void close() {
        connection.close();
    }
}
