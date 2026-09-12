package com.lfonta.theowlery.types.interfaces;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.descriptors.CommandDescriptor;
import org.jetbrains.annotations.NotNull;

public interface SlashCommandContract {

    @NotNull
    CommandDescriptor getDescriptor();

    void execute(CommandContext context);
}
