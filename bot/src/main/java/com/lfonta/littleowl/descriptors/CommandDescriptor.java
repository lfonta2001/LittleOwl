package com.lfonta.littleowl.descriptors;

import lombok.Builder;
import lombok.Singular;
import com.lfonta.littleowl.types.enums.CommandCategory;
import com.lfonta.littleowl.types.enums.GuildPermissions;

import java.util.List;

@Builder
public record CommandDescriptor(
        String id,
        CommandCategory category,
        int cooldown,
        boolean guildOnly,
        boolean ownerOnly,
        boolean personalOnly,
        @Singular
        List<GuildPermissions> permissions,
        @Singular
        List<CommandOptionsDescriptor> options,
        @Singular
        List<SubCommandDescriptor> subcommands) {
}
