package com.lfonta.littleowl.middlewares;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.services.CooldownService;
import com.lfonta.littleowl.services.ModerationService;
import com.lfonta.littleowl.types.annotations.Middleware;
import com.lfonta.littleowl.types.enums.MiddlewareResult;
import com.lfonta.littleowl.types.interfaces.CommandMiddlewareContract;

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
