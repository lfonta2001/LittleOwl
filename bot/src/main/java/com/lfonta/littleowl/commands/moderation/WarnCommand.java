package com.lfonta.littleowl.commands.moderation;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.descriptors.CommandDescriptor;
import com.lfonta.littleowl.descriptors.CommandOptionsDescriptor;
import com.lfonta.littleowl.services.MessageService;
import com.lfonta.littleowl.services.ModerationService;
import com.lfonta.littleowl.types.annotations.SlashCommand;
import com.lfonta.littleowl.types.enums.CommandCategory;
import com.lfonta.littleowl.types.enums.CommandOptionType;
import com.lfonta.littleowl.types.enums.GuildPermissions;
import com.lfonta.littleowl.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;

@SlashCommand
public class WarnCommand implements SlashCommandContract {

    private final ModerationService moderationService;
    private final MessageService messageService;

    public WarnCommand(ModerationService moderationService, MessageService messageService) {
        this.moderationService = moderationService;
        this.messageService = messageService;
    }

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        CommandOptionsDescriptor userOption = CommandOptionsDescriptor.builder()
                .id("user")
                .required(true)
                .type(CommandOptionType.USER)
                .min(1)
                .max(1)
                .build();

        CommandOptionsDescriptor reasonOption = CommandOptionsDescriptor.builder()
                .id("reason")
                .required(false)
                .type(CommandOptionType.STRING)
                .min(1)
                .max(100)
                .build();

        return CommandDescriptor.builder()
                .id("warn")
                .category(CommandCategory.MODERATION)
                .cooldown(15)
                .guildOnly(true)
                .permission(GuildPermissions.BAN_MEMBERS)
                .option(userOption)
                .option(reasonOption)
                .build();
    }

    public void execute(CommandContext context) {
        String targetUser = context.getRequiredOption("user", CommandOptionType.USER);
        String reason = context.getOptionalOption("reason", CommandOptionType.STRING);

        moderationService.warn(
                context,
                targetUser,
                reason
        );

        messageService.sendEphemeralReply(context, "command.warn.reply");
    }
}

