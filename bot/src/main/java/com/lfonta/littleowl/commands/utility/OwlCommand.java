package com.lfonta.littleowl.commands.utility;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.descriptors.CommandDescriptor;
import com.lfonta.littleowl.services.MessageService;
import com.lfonta.littleowl.types.annotations.SlashCommand;
import com.lfonta.littleowl.types.enums.CommandCategory;
import com.lfonta.littleowl.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;

@SlashCommand
public class OwlCommand implements SlashCommandContract {

    private final MessageService messageService;

    public OwlCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("owl")
                .category(CommandCategory.UTILITY)
                .cooldown(30)
                .guildOnly(true)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        messageService.sendReply(context, "command.owl.reply");
    }
}
