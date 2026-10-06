package com.lfonta.littleowl.exceptions;

import com.lfonta.littleowl.contexts.CommandContext;

import java.util.Map;

public class CooldownException extends CommandException {
    public CooldownException(CommandContext context, Map<String, Object> messageParams) {
        super(context, "error.command_on_cooldown", messageParams);
    }
}
