package com.lfonta.littleowl.exceptions;

import com.lfonta.littleowl.contexts.CommandContext;

public class NotGuildExecutedException extends CommandException {
    public NotGuildExecutedException(CommandContext context) {
        super(context, "error.guild_only");
    }
}
