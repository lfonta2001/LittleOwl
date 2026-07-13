package online.theowlery.types.interfaces;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import org.jetbrains.annotations.NotNull;

public interface SlashCommandContract {

    @NotNull
    CommandDescriptor getDescriptor();

    void execute(CommandContext context);
}
