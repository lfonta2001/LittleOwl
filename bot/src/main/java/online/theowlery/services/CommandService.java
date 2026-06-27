package online.theowlery.services;

import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.Service;

import java.util.*;

@Service
public class CommandService {

    private final Map<String, ISlashCommand> commands = new HashMap<>();

    private final JDA client;

    public CommandService(JDA client, List<ISlashCommand> commands) {
        this.client = client;
        commands.forEach(command -> {
            this.commands.put(command.getDescriptor().name(), command);
        });
    }

    public void loadCommands() {
        List<CommandData> commandData = commands
                .values().stream()
                .map(ISlashCommand::getDescriptor)
                .map(CommandMapper::getCommandData)
                .toList();

        client.updateCommands()
                .addCommands(commandData)
                .queue();
    }

    public Optional<ISlashCommand> get(String name) {
        return Optional.ofNullable(commands.get(name));
    }

    public List<ISlashCommand> getCommands() {
        return new ArrayList<>(commands.values());
    }
}
