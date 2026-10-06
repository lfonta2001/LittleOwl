package com.lfonta.littleowl.core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.exceptions.UnknownCommandException;
import com.lfonta.littleowl.services.CommandService;
import com.lfonta.littleowl.services.ContextService;
import com.lfonta.littleowl.services.MessageService;
import com.lfonta.littleowl.types.InteractionResponse;
import com.lfonta.littleowl.types.annotations.Handler;
import com.lfonta.littleowl.types.interfaces.SlashCommandContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Handler
public class CommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);

    private final ContextService contextService;
    private final CommandService commandService;
    private final MessageService messageService;
    private final MiddlewareHandler middlewareHandler;
    private final ExceptionHandler exceptionHandler;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public CommandHandler(
            ContextService contextService,
            CommandService commandService,
            MessageService messageService,
            MiddlewareHandler middlewareHandler,
            ExceptionHandler exceptionHandler) {
        this.contextService = contextService;
        this.commandService = commandService;
        this.messageService = messageService;
        this.middlewareHandler = middlewareHandler;
        this.exceptionHandler = exceptionHandler;
    }

    public void handle(SlashCommandInteractionEvent event) {
        try {
            long startTime = System.nanoTime();
            InteractionResponse interaction = messageService.register(event);
            Optional<SlashCommandContract> posCommand = commandService.get(event.getName());

            if (posCommand.isEmpty()) {
                throw new UnknownCommandException(event.getInteraction());
            }

            SlashCommandContract command = posCommand.get();
            String restOfMessage = "in private chat.";

            if (event.isFromGuild()) {
                restOfMessage = "in guild " + event.getGuild().getId();
            }

            logger.info("Executing command /{} by user {} {}", command.getDescriptor().id(), event.getUser().getId(), restOfMessage);

            ScheduledFuture<?> deferTask = scheduler.schedule(
                    () -> {
                        messageService.deferReply(interaction);
                    },
                    1500,
                    TimeUnit.MILLISECONDS
            );

            CommandContext context = contextService.createCommandContext(interaction, command);

            try {

                middlewareHandler.execute(context);

                command.execute(context);

            } finally {
                deferTask.cancel(false);
            }
            long endTime = System.nanoTime();
            logger.info("Command /{} executed by user {} successfully in {}ms", context.commandInformation().id(), context.interactionsUser().getId(), (endTime - startTime) / 1_000_000);
        } catch (Exception e) {
            logger.error("Failed to execute command");
            exceptionHandler.handle(e);
        }
    }
}
