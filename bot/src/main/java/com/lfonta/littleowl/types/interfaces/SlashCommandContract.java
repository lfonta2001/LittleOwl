package com.lfonta.littleowl.types.interfaces;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.descriptors.CommandDescriptor;
import org.jetbrains.annotations.NotNull;

public interface SlashCommandContract {

    @NotNull
    CommandDescriptor getDescriptor();

    void execute(CommandContext context);
}
