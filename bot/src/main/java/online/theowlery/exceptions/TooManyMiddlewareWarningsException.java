package online.theowlery.exceptions;

import online.theowlery.contexts.CommandContext;

public class TooManyMiddlewareWarningsException extends CommandException {
    public TooManyMiddlewareWarningsException(CommandContext context) {
        super(context, "error.middleware_warnings_exceeded");
    }
}
