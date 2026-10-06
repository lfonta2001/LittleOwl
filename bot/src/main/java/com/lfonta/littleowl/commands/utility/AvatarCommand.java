package com.lfonta.littleowl.commands.utility;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.descriptors.CommandDescriptor;
import com.lfonta.littleowl.services.UserService;
import com.lfonta.littleowl.types.annotations.SlashCommand;
import com.lfonta.littleowl.types.enums.CommandCategory;
import com.lfonta.littleowl.types.interfaces.SlashCommandContract;
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
