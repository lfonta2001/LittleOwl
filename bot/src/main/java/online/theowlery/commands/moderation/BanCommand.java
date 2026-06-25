package online.theowlery.commands.moderation;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;

@SlashCommand
public class BanCommand implements ISlashCommand {

    @Override
    public CommandDescriptor getDescriptor() {
        return new CommandDescriptor(
                "ban",
                "Command used to ban someone",
                CommandCategory.MODERATION,
                30,
                false,
                false,
                null
        );
    }

    @Override
    public void execute(CommandContext context) {
    }
}
