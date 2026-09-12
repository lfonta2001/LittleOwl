package com.lfonta.theowlery.middlewares;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.services.CooldownService;
import com.lfonta.theowlery.services.ModerationService;
import com.lfonta.theowlery.types.annotations.Middleware;
import com.lfonta.theowlery.types.enums.MiddlewareResult;
import com.lfonta.theowlery.types.interfaces.CommandMiddlewareContract;

@Middleware
public class CooldownMiddleware implements CommandMiddlewareContract {
    private final CooldownService cooldownService;
    private final ModerationService moderationService;

    public CooldownMiddleware(CooldownService cooldownService, ModerationService moderationService) {
        this.cooldownService = cooldownService;
        this.moderationService = moderationService;
    }

    public MiddlewareResult run(CommandContext context) {
        return MiddlewareResult.CONTINUE;
    }
}
