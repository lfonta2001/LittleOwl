package com.lfonta.littleowl.persistence.repositories;

import com.lfonta.littleowl.persistence.entities.GuildEntity;
import com.lfonta.littleowl.persistence.repositories.interfaces.GuildRepository;
import org.jooq.DSLContext;

import java.util.Optional;

public class jOOQGuildRepository extends GuildRepository {

    public jOOQGuildRepository(DSLContext dsl) {
        super(dsl);
    }

    @Override
    public Optional<GuildEntity> findById(long guildId) {
        // TODO query jOOQ
        return Optional.empty();
    }

    @Override
    public void save(GuildEntity guild) {
        // TODO insert/update
    }

    @Override
    public void deleteById(long guildId) {
        // TODO delete
    }
}
