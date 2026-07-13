package online.theowlery.core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import online.theowlery.contexts.CommandContext;
import online.theowlery.services.CommandService;
import online.theowlery.services.ContextService;
import online.theowlery.services.MessageService;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Handler
public class CommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);

    private final ContextService contextService;
    private final CommandService commandService;
    private final MiddlewareHandler middlewareHandler;
    private final MessageService messageService;

    public CommandHandler(
            ContextService contextService,
            CommandService commandService,
            MiddlewareHandler middlewareHandler,
            MessageService messageService) {
        this.contextService = contextService;
        this.commandService = commandService;
        this.middlewareHandler = middlewareHandler;
        this.messageService = messageService;
    }

    public void handle(SlashCommandInteractionEvent event) {
        Optional<ISlashCommand> posCommand = commandService.get(event.getName());

        if (posCommand.isEmpty()) {
            messageService.sendReply(event.getInteraction(), "No se encontró el comando");
            return;
        }

        ISlashCommand command = posCommand.get();

            logger.info("Executing command /{} by user {} {}", command.getDescriptor().id(), event.getUser().getId(), event.getGuild().getId());

        if (command.getDescriptor().longExecution()) {
            messageService.deferReply(event.getInteraction());
        }

        CommandContext context = contextService.createCommandContext(event);

        middlewareHandler.execute(context, command);

        command.execute(context);
    }
}
