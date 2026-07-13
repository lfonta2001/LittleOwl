package online.theowlery.types.interfaces;

import online.theowlery.contexts.CommandContext;
import online.theowlery.types.enums.MiddlewareResult;

public interface CommandMiddlewareContract {
    MiddlewareResult run(CommandContext context);
}
