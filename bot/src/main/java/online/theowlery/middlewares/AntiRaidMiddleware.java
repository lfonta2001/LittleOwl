package online.theowlery.middlewares;

import online.theowlery.types.IMiddleware;
import online.theowlery.types.annotations.Middleware;
import online.theowlery.types.enums.MiddlewareResult;

@Middleware
public class AntiRaidMiddleware implements IMiddleware {
    public MiddlewareResult run() {
        return MiddlewareResult.CONTINUE;
    }
}
