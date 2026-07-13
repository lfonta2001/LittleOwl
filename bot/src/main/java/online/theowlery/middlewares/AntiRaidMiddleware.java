package online.theowlery.middlewares;

import online.theowlery.contexts.CommandContext;
import online.theowlery.contexts.EventContext;
import online.theowlery.types.annotations.Middleware;
import online.theowlery.types.enums.MiddlewareResult;
import online.theowlery.types.interfaces.CommandMiddlewareContract;
import online.theowlery.types.interfaces.EventMiddlewareContract;


@Middleware
public class AntiRaidMiddleware implements CommandMiddlewareContract, EventMiddlewareContract {
    public MiddlewareResult run(CommandContext context) {
        return MiddlewareResult.CONTINUE;
    }

    public MiddlewareResult run(EventContext context) {
        return MiddlewareResult.CONTINUE;
    }
}
