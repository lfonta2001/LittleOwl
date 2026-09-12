package com.lfonta.theowlery.commands.utility;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.descriptors.CommandDescriptor;
import com.lfonta.theowlery.services.UserService;
import com.lfonta.theowlery.types.annotations.SlashCommand;
import com.lfonta.theowlery.types.enums.CommandCategory;
import com.lfonta.theowlery.types.interfaces.SlashCommandContract;
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
