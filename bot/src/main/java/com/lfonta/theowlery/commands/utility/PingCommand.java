package com.lfonta.theowlery.commands.utility;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.descriptors.CommandDescriptor;
import com.lfonta.theowlery.services.MessageService;
import com.lfonta.theowlery.types.annotations.SlashCommand;
import com.lfonta.theowlery.types.enums.CommandCategory;
import com.lfonta.theowlery.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;

@SlashCommand
public class PingCommand implements SlashCommandContract {

    private final MessageService messageService;

    public PingCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("ping")
                .category(CommandCategory.UTILITY)
                .cooldown(2)
                .guildOnly(false)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
        messageService.sendReply(context, "command.ping.reply");
    }
}
