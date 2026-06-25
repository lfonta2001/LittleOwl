package online.theowlery.commands.utility;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.services.UserService;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;

@SlashCommand
public class AvatarCommand implements ISlashCommand {

    private final UserService userService;

    public AvatarCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandDescriptor getDescriptor() {
        return new CommandDescriptor(
                "avatar",
                "Retrieves user avatar image.",
                CommandCategory.UTILITY,
                30,
                true,
                false,
                null
        );
    }

    @Override
    public void execute(CommandContext context) {
    }
}
