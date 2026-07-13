package online.theowlery.descriptors;

import lombok.Builder;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.enums.GuildPermissions;

import java.util.List;

@Builder
public record CommandDescriptor(
        String id,
        CommandCategory category,
        int cooldown,
        boolean guildOnly,
        boolean ownerOnly,
        boolean personalOnly,
        boolean longExecution,
        List<GuildPermissions> permissions,
        List<CommandOptionsDescriptor> options,
        List<SubCommandDescriptor> subcommands) {
}
