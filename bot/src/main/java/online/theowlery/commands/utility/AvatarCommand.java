package online.theowlery.commands.utility;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.services.UserService;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;

@SlashCommand
public class AvatarCommand implements SlashCommandContract {

    private final UserService userService;

    public AvatarCommand(UserService userService) {
        this.userService = userService;
    }

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("avatar")
                .category(CommandCategory.UTILITY)
                .cooldown(30)
                .guildOnly(true)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
    }
}
