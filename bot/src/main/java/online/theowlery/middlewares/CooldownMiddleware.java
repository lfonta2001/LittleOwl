package online.theowlery.middlewares;

import online.theowlery.services.CooldownService;
import online.theowlery.services.ModerationService;
import online.theowlery.types.IMiddleware;
import online.theowlery.types.annotations.Middleware;
import online.theowlery.types.enums.MiddlewareResult;

@Middleware
public class CooldownMiddleware implements IMiddleware {
    private final CooldownService cooldownService;
    private final ModerationService moderationService;

    public CooldownMiddleware(CooldownService cooldownService, ModerationService moderationService) {
        this.cooldownService = cooldownService;
        this.moderationService = moderationService;
    }

    public MiddlewareResult run() {

        return MiddlewareResult.CONTINUE;
    }
}
