package com.lfonta.littleowl.persistence.repositories.interfaces;

import com.lfonta.littleowl.persistence.entities.UserEntity;
import org.jooq.DSLContext;

public abstract class UserRepository extends Repository<UserEntity> {

    public UserRepository(DSLContext dsl) {
        super(dsl);
    }
}
