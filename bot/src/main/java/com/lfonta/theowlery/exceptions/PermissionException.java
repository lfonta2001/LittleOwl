package com.lfonta.theowlery.exceptions;

import com.lfonta.theowlery.contexts.CommandContext;

public class PermissionException extends CommandException {
    public PermissionException(CommandContext context) {
        super(context, "error.missing_permissions");
    }
}
