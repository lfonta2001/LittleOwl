package com.lfonta.theowlery.descriptors;

import net.dv8tion.jda.api.Permission;
import com.lfonta.theowlery.types.enums.CommandCategory;

import java.util.List;
import java.util.Vector;

public record SubCommandDescriptor(
        String id,
        CommandCategory category,
        int cooldown,
        boolean guildOnly,
        boolean ownerOnly,
        Vector<Permission> permissions,
        List<CommandOptionsDescriptor> options) {
}
