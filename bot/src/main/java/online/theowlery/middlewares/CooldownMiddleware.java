package online.theowlery.middlewares;

import online.theowlery.contexts.CommandContext;
import online.theowlery.services.CooldownService;
import online.theowlery.services.ModerationService;
import online.theowlery.types.annotations.Middleware;
import online.theowlery.types.enums.MiddlewareResult;
import online.theowlery.types.interfaces.CommandMiddlewareContract;

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
