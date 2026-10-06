package com.lfonta.littleowl.types.interfaces;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.types.enums.MiddlewareResult;

public interface CommandMiddlewareContract {
    MiddlewareResult run(CommandContext context);
}
