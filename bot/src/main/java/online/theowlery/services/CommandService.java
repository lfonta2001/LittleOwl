package online.theowlery.services;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import online.theowlery.mappers.CommandMapper;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.Service;

import java.util.*;

@Service
public class CommandService {

    private final Map<String, ISlashCommand> commands = new HashMap<>();

    private final JDA client;
    private final GuildService guildService;

    public CommandService(JDA client, GuildService guildService, List<ISlashCommand> commands) {
        this.client = client;
        this.guildService = guildService;
        commands.forEach(command -> this.commands.put(command.getDescriptor().name(), command));
    }

    public void loadCommands() {
        List<ISlashCommand> guildCommands = commands.values().stream()
                .filter(cmd -> cmd.getDescriptor().guildOnly())
                .toList();

        List<ISlashCommand> globalCommands = commands.values().stream()
                .filter(cmd -> !cmd.getDescriptor().guildOnly())
                .toList();

        List<CommandData> guildCommandsData = guildCommands.stream()
                .map(ISlashCommand::getDescriptor)
                .map(CommandMapper::getCommandData)
                .toList();

        List<CommandData> globalCommandsData = globalCommands.stream()
                .map(ISlashCommand::getDescriptor)
                .map(CommandMapper::getCommandData)
                .toList();

        for (Guild guild : guildService.getGuilds()) {
            guild.updateCommands()
                    .addCommands(guildCommandsData)
                    .queue();
        }

        client.updateCommands()
                .addCommands(globalCommandsData)
                .queue();
    }

    public Optional<ISlashCommand> get(String name) {
        return Optional.ofNullable(commands.get(name));
    }

    public List<ISlashCommand> getCommands() {
        return new ArrayList<>(commands.values());
    }
}
