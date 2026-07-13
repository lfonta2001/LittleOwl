package online.theowlery.commands.moderation;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.enums.GuildPermissions;
import online.theowlery.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SlashCommand
public class WarnCommand implements SlashCommandContract {

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("warn")
                .category(CommandCategory.MODERATION)
                .cooldown(15)
                .guildOnly(true)
                .permissions(List.of(GuildPermissions.ADMINISTRATOR))
                .build();
    }

    public void execute(CommandContext context) {
        // Use warn service or something to warn a user about something
    }
}
