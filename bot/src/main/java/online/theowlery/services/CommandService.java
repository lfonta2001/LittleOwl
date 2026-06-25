package online.theowlery.services;

import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.Service;

import java.util.*;

@Service
public class CommandService {

    private final Map<String, ISlashCommand> commands = new HashMap<>();

    public void loadCommands(List<ISlashCommand> commands) {
        for (ISlashCommand command : commands) {
            this.commands.put(command.getDescriptor().name(), command);
        }
    }

    public Optional<ISlashCommand> get(String name) {
        return Optional.ofNullable(commands.get(name));
    }

    public List<ISlashCommand> getCommands() {
        return new ArrayList<>(commands.values());
    }
}
