package com.lfonta.littleowl.commands.moderation;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.descriptors.CommandDescriptor;
import com.lfonta.littleowl.types.annotations.SlashCommand;
import com.lfonta.littleowl.types.enums.CommandCategory;
import com.lfonta.littleowl.types.enums.GuildPermissions;
import com.lfonta.littleowl.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

@SlashCommand
public class BanCommand implements SlashCommandContract {

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("ban")
                .category(CommandCategory.MODERATION)
                .cooldown(30)
                .guildOnly(true)
                .permissions(Collections.singletonList(GuildPermissions.BAN_MEMBERS))
                .build();
    }

    @Override
    public void execute(CommandContext context) {
    }
}
