package com.lfonta.theowlery.middlewares;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.contexts.EventContext;
import com.lfonta.theowlery.types.annotations.Middleware;
import com.lfonta.theowlery.types.enums.MiddlewareResult;
import com.lfonta.theowlery.types.interfaces.CommandMiddlewareContract;
import com.lfonta.theowlery.types.interfaces.EventMiddlewareContract;


@Middleware
public class AntiRaidMiddleware implements CommandMiddlewareContract, EventMiddlewareContract {
    public MiddlewareResult run(CommandContext context) {
        return MiddlewareResult.CONTINUE;
    }

    public MiddlewareResult run(EventContext context) {
        return MiddlewareResult.CONTINUE;
    }
}
