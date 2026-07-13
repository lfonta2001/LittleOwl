package online.theowlery.core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import online.theowlery.contexts.CommandContext;
import online.theowlery.exceptions.UnknownCommandException;
import online.theowlery.services.CommandService;
import online.theowlery.services.ContextService;
import online.theowlery.services.MessageService;
import online.theowlery.types.annotations.Handler;
import online.theowlery.types.interfaces.SlashCommandContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Handler
public class CommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);

    private final ContextService contextService;
    private final CommandService commandService;
    private final MessageService messageService;
    private final MiddlewareHandler middlewareHandler;
    private final ExceptionHandler exceptionHandler;

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
            Optional<SlashCommandContract> posCommand = commandService.get(event.getName());

            if (posCommand.isEmpty()) {
                throw new UnknownCommandException(event.getInteraction());
            }

            SlashCommandContract command = posCommand.get();

            logger.info("Executing command /{} by user {} {}", command.getDescriptor().id(), event.getUser().getId(), event.getGuild().getId());

            if (command.getDescriptor().longExecution()) {
                messageService.deferReply(event.getInteraction());
            }

            CommandContext context = contextService.createCommandContext(event, command);

            middlewareHandler.execute(context);

            command.execute(context);
            long endTime = System.nanoTime();
            logger.info("Command /{} executed by user {} successfully in {}ms", context.commandInformation().id(), context.interactionsUser().getId(), (endTime - startTime) / 1_000_000);
        } catch (Exception e) {
            exceptionHandler.handle(e);
        }
    }
}
