package com.lfonta.littleowl.commands.utility;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.descriptors.CommandDescriptor;
import com.lfonta.littleowl.services.MessageService;
import com.lfonta.littleowl.types.annotations.SlashCommand;
import com.lfonta.littleowl.types.enums.CommandCategory;
import com.lfonta.littleowl.types.interfaces.SlashCommandContract;
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
