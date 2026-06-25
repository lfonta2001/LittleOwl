package online.theowlery.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import online.theowlery.contexts.ApplicationContext;
import online.theowlery.listeners.InteractionListener;
import online.theowlery.listeners.client.ReadyListener;
import online.theowlery.mappers.CommandMapper;
import online.theowlery.services.CommandService;
import online.theowlery.types.ISlashCommand;

import java.util.List;

public class Loader {

    public static void mainLoad(JDA client, ApplicationContext applicationContext) {
        client.addEventListener(new InteractionListener(applicationContext.getBean(CommandHandler.class)), new ReadyListener());
        loadCommands(client, applicationContext);
    }

    public static void loadCommands(JDA client, ApplicationContext context) {
        CommandService commandService = context.getBean(CommandService.class);

        List<ISlashCommand> commands = context.getBeansOfType(ISlashCommand.class);

        commandService.loadCommands(commands);

        List<CommandData> commandData = commands
                .stream()
                .map(ISlashCommand::getDescriptor)
                .map(CommandMapper::getCommandData)
                .toList();

        client.updateCommands()
                .addCommands(commandData)
                .queue();
    }
}
