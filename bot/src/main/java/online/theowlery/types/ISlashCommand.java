package online.theowlery.types;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;

public interface ISlashCommand {

    CommandDescriptor getDescriptor();

    void execute(CommandContext context);
}
