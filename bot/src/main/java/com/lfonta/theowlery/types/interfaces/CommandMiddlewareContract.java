package com.lfonta.theowlery.types.interfaces;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.types.enums.MiddlewareResult;

public interface CommandMiddlewareContract {
    MiddlewareResult run(CommandContext context);
}
