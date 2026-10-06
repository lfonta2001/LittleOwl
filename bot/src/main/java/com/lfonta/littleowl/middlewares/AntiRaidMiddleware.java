package com.lfonta.littleowl.middlewares;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.contexts.EventContext;
import com.lfonta.littleowl.types.annotations.Middleware;
import com.lfonta.littleowl.types.enums.MiddlewareResult;
import com.lfonta.littleowl.types.interfaces.CommandMiddlewareContract;
import com.lfonta.littleowl.types.interfaces.EventMiddlewareContract;


@Middleware
public class AntiRaidMiddleware implements CommandMiddlewareContract, EventMiddlewareContract {
    public MiddlewareResult run(CommandContext context) {
        return MiddlewareResult.CONTINUE;
    }

    public MiddlewareResult run(EventContext context) {
        return MiddlewareResult.CONTINUE;
    }
}
