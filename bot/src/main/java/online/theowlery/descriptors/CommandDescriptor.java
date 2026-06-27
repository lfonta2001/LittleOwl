package online.theowlery.descriptors;

import lombok.Builder;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.enums.GuildPermissions;

import java.util.List;
import java.util.Vector;

@Builder
public record CommandDescriptor(
        String name,
        String description,
        CommandCategory category,
        int cooldown,
        boolean guildOnly,
        boolean ownerOnly,
        Vector<GuildPermissions> permissions,
        List<CommandOptionsDescriptor> options,
        List<SubCommandDescriptor> subcommands) {
}
