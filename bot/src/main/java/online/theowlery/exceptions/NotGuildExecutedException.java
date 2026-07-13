package online.theowlery.exceptions;

import online.theowlery.contexts.CommandContext;

public class NotGuildExecutedException extends CommandException {
    public NotGuildExecutedException(CommandContext context) {
        super(context, "error.guild_only");
    }
}
