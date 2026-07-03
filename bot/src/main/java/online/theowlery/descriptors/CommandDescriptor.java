package online.theowlery.descriptors;

import lombok.Builder;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.enums.GuildPermissions;

import java.util.List;

@Builder
public record CommandDescriptor(
        String name,
        String description,
        CommandCategory category,
        int cooldown,
        boolean guildOnly,
        boolean ownerOnly,
        /* boolean personalOnly, usar esto para comandos que solo se puedan usar en el chat con el bot, por ejemplo configuraciones personales*/
        boolean longExecution,
        List<GuildPermissions> permissions,
        List<CommandOptionsDescriptor> options,
        List<SubCommandDescriptor> subcommands) {
}
