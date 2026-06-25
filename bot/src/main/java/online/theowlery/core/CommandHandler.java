package online.theowlery.core;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import online.theowlery.contexts.CommandContext;
import online.theowlery.services.CommandService;
import online.theowlery.services.ContextService;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.Handler;

import java.util.Optional;

@Handler
public class CommandHandler {

    private final ContextService contextService;
    private final CommandService commandService;
    private final MiddlewareHandler middlewareHandler;

    public CommandHandler(ContextService contextService, CommandService commandService, MiddlewareHandler middlewareHandler) {
        this.contextService = contextService;
        this.commandService = commandService;
        this.middlewareHandler = middlewareHandler;
    }

    public void handle(SlashCommandInteractionEvent event) {
        System.out.println("[CommandHandler] Se esta manejando esto mi brother del alma, no te toques");

        CommandContext context = contextService.createCommandContext(event);

        Optional<ISlashCommand> posCommand = commandService.get(event.getName());

        if (posCommand.isEmpty()) return;

        ISlashCommand command = posCommand.get();

        middlewareHandler.execute(context, command);

        command.execute(context);
    }
}
