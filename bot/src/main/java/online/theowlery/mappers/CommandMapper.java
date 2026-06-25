package online.theowlery.mappers;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import online.theowlery.descriptors.CommandDescriptor;

public class CommandMapper {

    public static CommandData getCommandData(CommandDescriptor descriptor) {
        return Commands.slash(
                descriptor.name(),
                descriptor.description()
        );
    }
}
