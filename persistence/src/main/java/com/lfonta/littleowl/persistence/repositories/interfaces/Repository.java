package com.lfonta.littleowl.persistence.repositories.interfaces;

import org.jooq.DSLContext;

import java.util.Optional;

public abstract class Repository<T> {

    protected final DSLContext dsl;

    public Repository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public abstract Optional<T> findById(long id);

    // Works as create and update
    public abstract void save(T t);

    public abstract void deleteById(long id);
}
