package com.lfonta.littleowl.persistence.repositories;

import com.lfonta.littleowl.persistence.entities.UserEntity;
import com.lfonta.littleowl.persistence.repositories.interfaces.UserRepository;
import org.jooq.DSLContext;

import java.util.Optional;

public class jOOQUserRepository extends UserRepository {

    public jOOQUserRepository(DSLContext dsl) {
        super(dsl);
    }

    @Override
    public Optional<UserEntity> findById(long userId) {
        // TODO query
        return Optional.empty();
    }

    @Override
    public void save(UserEntity user) {
        // TODO save
    }

    @Override
    public void deleteById(long userId) {
        // TODO delete
    }
}
