package online.theowlery.services;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import online.theowlery.core.ExceptionHandler;
import online.theowlery.mappers.CommandMapper;
import online.theowlery.types.annotations.Service;
import online.theowlery.types.interfaces.SlashCommandContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class CommandService {

    private static final Logger log = LoggerFactory.getLogger(CommandService.class);

    private final Map<String, SlashCommandContract> commands = new HashMap<>();

    private final JDA client;
    private final GuildService guildService;
    private final CommandMapper commandMapper;

    private final ExceptionHandler exceptionHandler;

    public CommandService(JDA client,
                          GuildService guildService,
                          CommandMapper commandMapper,
                          ExceptionHandler exceptionHandler,
                          List<SlashCommandContract> commands) {
        this.client = client;
        this.guildService = guildService;
        this.commandMapper = commandMapper;
        this.exceptionHandler = exceptionHandler;
        commands.forEach(command -> this.commands.put(command.getDescriptor().id(), command));
    }

    public void loadCommands() {
        try {
            List<SlashCommandContract> guildCommands = commands.values().stream()
                    .filter(cmd -> cmd.getDescriptor().guildOnly())
                    .toList();

            List<SlashCommandContract> globalCommands = commands.values().stream()
                    .filter(cmd -> !cmd.getDescriptor().guildOnly())
                    .toList();

            List<CommandData> guildCommandsData = guildCommands.stream()
                    .map(SlashCommandContract::getDescriptor)
                    .map(commandMapper::getCommandData)
                    .toList();

            List<CommandData> globalCommandsData = globalCommands.stream()
                    .map(SlashCommandContract::getDescriptor)
                    .map(commandMapper::getCommandData)
                    .toList();

            for (Guild guild : guildService.getGuilds().values()) {
                guild.updateCommands()
                        .addCommands(guildCommandsData)
                        .queue();
            }

            client.updateCommands()
                    .addCommands(globalCommandsData)
                    .queue();

            log.info("Commands loaded:\n\tGlobal: {}\n\tGuild: {}", globalCommands.size(), guildCommands.size());
        } catch (Throwable e) {
            exceptionHandler.handle(e);
        }
    }

    public Optional<SlashCommandContract> get(String name) {
        return Optional.ofNullable(commands.get(name));
    }

    public List<SlashCommandContract> getCommands() {
        return new ArrayList<>(commands.values());
    }
}
