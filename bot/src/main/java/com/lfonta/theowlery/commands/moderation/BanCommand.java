package com.lfonta.theowlery.commands.moderation;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.descriptors.CommandDescriptor;
import com.lfonta.theowlery.types.annotations.SlashCommand;
import com.lfonta.theowlery.types.enums.CommandCategory;
import com.lfonta.theowlery.types.enums.GuildPermissions;
import com.lfonta.theowlery.types.interfaces.SlashCommandContract;
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
