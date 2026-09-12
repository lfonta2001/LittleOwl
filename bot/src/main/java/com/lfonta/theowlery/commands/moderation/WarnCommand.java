package com.lfonta.theowlery.commands.moderation;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.descriptors.CommandDescriptor;
import com.lfonta.theowlery.descriptors.CommandOptionsDescriptor;
import com.lfonta.theowlery.services.MessageService;
import com.lfonta.theowlery.services.ModerationService;
import com.lfonta.theowlery.types.annotations.SlashCommand;
import com.lfonta.theowlery.types.enums.CommandCategory;
import com.lfonta.theowlery.types.enums.CommandOptionType;
import com.lfonta.theowlery.types.enums.GuildPermissions;
import com.lfonta.theowlery.types.interfaces.SlashCommandContract;
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

