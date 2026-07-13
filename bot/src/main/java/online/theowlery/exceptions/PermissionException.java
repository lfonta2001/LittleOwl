package online.theowlery.exceptions;

import online.theowlery.contexts.CommandContext;

public class PermissionException extends CommandException {
    public PermissionException(CommandContext context) {
        super(context, "error.missing_permissions");
    }
}
