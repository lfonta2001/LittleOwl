package online.theowlery.types.interfaces;

import online.theowlery.contexts.EventContext;
import online.theowlery.types.enums.MiddlewareResult;

public interface EventMiddlewareContract {
    MiddlewareResult run(EventContext context);
}
