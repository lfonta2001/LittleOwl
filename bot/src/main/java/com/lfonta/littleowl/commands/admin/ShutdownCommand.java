package com.lfonta.littleowl.commands.admin;

import net.dv8tion.jda.api.JDA;
import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.descriptors.CommandDescriptor;
import com.lfonta.littleowl.services.MessageService;
import com.lfonta.littleowl.types.annotations.SlashCommand;
import com.lfonta.littleowl.types.enums.CommandCategory;
import com.lfonta.littleowl.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SlashCommand
public class ShutdownCommand implements SlashCommandContract {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownCommand.class);

    private final JDA client;

    private final MessageService messageService;

    public ShutdownCommand(JDA client, MessageService messageService) {
        this.client = client;
        this.messageService = messageService;
    }

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("shutdown")
                .category(CommandCategory.BOT_OWNER)
                .ownerOnly(true)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
        String ownerId = System.getenv("BOT_OWNER_DISCORD_ID");

        if (ownerId == null) {
            logger.error("Owner ID not found in environmental variables");
            messageService.sendCommandUnableToExecute(context);
            return;
        }

        if (context.userData().id().equals(ownerId)) {
            messageService.sendReply(context, "command.shutdown.success_reply");
            client.shutdown();
        } else {
            messageService.sendReply(context, "command.shutdown.not_owner_reply");
        }
    }
}
