package online.theowlery.descriptors;

import net.dv8tion.jda.api.Permission;
import online.theowlery.types.enums.CommandCategory;

import java.util.Vector;

public record CommandDescriptor(
        String name,
        String description,
        CommandCategory category,
        int cooldown,
        boolean guildOnly,
        boolean ownerOnly,
        Vector<Permission> permissions) {
}
