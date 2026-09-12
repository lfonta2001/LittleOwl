package com.lfonta.theowlery.types.interfaces;

import com.lfonta.theowlery.contexts.EventContext;
import com.lfonta.theowlery.types.enums.MiddlewareResult;

public interface EventMiddlewareContract {
    MiddlewareResult run(EventContext context);
}
