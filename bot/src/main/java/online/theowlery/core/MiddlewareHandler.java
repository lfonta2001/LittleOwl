package online.theowlery.core;

import online.theowlery.contexts.CommandContext;
import online.theowlery.contexts.EventContext;
import online.theowlery.exceptions.CooldownException;
import online.theowlery.exceptions.PermissionException;
import online.theowlery.exceptions.TooManyMiddlewareWarningsException;
import online.theowlery.middlewares.CooldownMiddleware;
import online.theowlery.middlewares.PermissionMiddleware;
import online.theowlery.services.CooldownService;
import online.theowlery.services.WarnService;
import online.theowlery.types.annotations.Handler;
import online.theowlery.types.enums.MiddlewareResult;
import online.theowlery.types.interfaces.CommandMiddlewareContract;
import online.theowlery.types.interfaces.EventMiddlewareContract;
import online.theowlery.types.interfaces.SharedContext;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Handler
public class MiddlewareHandler {

    private final WarnService warnService;
    private final CooldownService cooldownService;

    private final List<CommandMiddlewareContract> commandMiddlewares;
    private final List<EventMiddlewareContract> eventMiddlewares;

    private static final int MAX_WARNINGS = 3;

    public MiddlewareHandler(WarnService warnService,
                             CooldownService cooldownService,
                             List<CommandMiddlewareContract> commandMiddlewares,
                             List<EventMiddlewareContract> eventMiddlewares) {
        this.warnService = warnService;
        this.cooldownService = cooldownService;
        this.commandMiddlewares = commandMiddlewares;
        this.eventMiddlewares = eventMiddlewares;
    }

    private <M, C extends SharedContext> void executeMiddlewares(
            List<M> middlewares,
            C context,
            BiFunction<M, C, MiddlewareResult> middlewareFunction
    ) {

        int warnCount = 0;

        for (M middleware : middlewares) {
            switch (middlewareFunction.apply(middleware, context)) {
                case CONTINUE -> {
                }
                case WARNING -> {
                    warnService.middlewareWarn(middleware.getClass(), context);
                    warnCount++;

                    if (warnCount >= MAX_WARNINGS) {
                        handleTooManyWarnings(context);
                    }
                }
                case STOP -> {
                    handleStoppedMiddleware(middleware, context);
                    return;
                }
            }
        }
    }

    private <M, C extends SharedContext> void handleStoppedMiddleware(M middleware, C context) {
        if (context instanceof CommandContext commandContext) {
            if (middleware instanceof CooldownMiddleware) {
                Map<String, Object> params = Map.of("TIME", cooldownService.getCooldown(commandContext));
                throw new CooldownException(commandContext, params);
            }

            if (middleware instanceof PermissionMiddleware) {
                throw new PermissionException(commandContext);
            }

            throw new TooManyMiddlewareWarningsException(commandContext);
        }

        warnService.middlewareWarn(middleware.getClass(), context);
    }

    private <C extends SharedContext> void handleTooManyWarnings(C context) {
        if (context instanceof CommandContext commandContext) {
            throw new TooManyMiddlewareWarningsException(commandContext);
        }

        warnService.middlewareWarn(MiddlewareHandler.class, context);
    }

    public void execute(CommandContext context) {
        executeMiddlewares(
                commandMiddlewares,
                context,
                CommandMiddlewareContract::run
        );
    }

    public void execute(EventContext context) {
        executeMiddlewares(
                eventMiddlewares,
                context,
                EventMiddlewareContract::run
        );
    }
}
