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
        return new CommandDescriptor(
                "ping",
                "Command to check if bot is up.",
                CommandCategory.UTILITY,
                2,
                false,
                false,
                null
        );
    }

    @Override
    public void execute(CommandContext context) {
        context.slashCommandInteractionEvent().reply("Pong!").queue();
    }
}
