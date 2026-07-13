package online.theowlery.exceptions;

import online.theowlery.contexts.CommandContext;

import java.util.Map;

public class CommandException extends BotException {
    public CommandException(CommandContext context, String messageKey) {
        super(context, messageKey);
    }

    public CommandException(CommandContext context, String messageKey, Map<String, Object> messageParams) {
        super(context, messageKey, messageParams);
    }
}
