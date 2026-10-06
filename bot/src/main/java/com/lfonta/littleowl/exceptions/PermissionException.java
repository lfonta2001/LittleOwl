package com.lfonta.littleowl.exceptions;

import com.lfonta.littleowl.contexts.CommandContext;

public class PermissionException extends CommandException {
    public PermissionException(CommandContext context) {
        super(context, "error.missing_permissions");
    }
}
