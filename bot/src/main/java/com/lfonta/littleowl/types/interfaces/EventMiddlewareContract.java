package com.lfonta.littleowl.types.interfaces;

import com.lfonta.littleowl.contexts.EventContext;
import com.lfonta.littleowl.types.enums.MiddlewareResult;

public interface EventMiddlewareContract {
    MiddlewareResult run(EventContext context);
}
