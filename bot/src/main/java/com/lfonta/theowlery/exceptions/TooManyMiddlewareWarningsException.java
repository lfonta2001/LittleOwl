package com.lfonta.theowlery.exceptions;

import com.lfonta.theowlery.contexts.CommandContext;

public class TooManyMiddlewareWarningsException extends CommandException {
    public TooManyMiddlewareWarningsException(CommandContext context) {
        super(context, "error.middleware_warnings_exceeded");
    }
}
