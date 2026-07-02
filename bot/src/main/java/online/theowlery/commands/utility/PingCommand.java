package online.theowlery.commands.utility;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;

@SlashCommand
public class PingCommand implements ISlashCommand {

    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .name("ping")
                .description("Command to check if bot is up.")
                .category(CommandCategory.UTILITY)
                .cooldown(2)
                .guildOnly(false)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
        context.slashCommandInteractionEvent().reply("Pong!").queue();
    }
}
