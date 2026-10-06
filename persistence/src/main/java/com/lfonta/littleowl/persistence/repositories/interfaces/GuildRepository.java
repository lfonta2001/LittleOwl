package com.lfonta.littleowl.persistence.repositories.interfaces;

import com.lfonta.littleowl.persistence.entities.GuildEntity;
import org.jooq.DSLContext;

public abstract class GuildRepository extends Repository<GuildEntity> {

    public GuildRepository(DSLContext dsl) {
        super(dsl);
    }
}
