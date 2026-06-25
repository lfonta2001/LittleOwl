package online.theowlery.core;

import online.theowlery.contexts.CommandContext;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.Handler;

@Handler
public class MiddlewareHandler {

    public void execute(CommandContext context, ISlashCommand event) {}
}
