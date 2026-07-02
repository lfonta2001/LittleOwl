package online.theowlery.core;

import net.dv8tion.jda.api.JDA;
import online.theowlery.services.CommandService;
import online.theowlery.services.GuildService;
import online.theowlery.types.annotations.Service;

@Service
public class Loader {

    private final JDA client;
    private final CommandService commandService;
    private final GuildService guildService;

    public Loader(JDA client, CommandService commandService, GuildService guildService) {
        this.client = client;
        this.commandService = commandService;
        this.guildService = guildService;
    }

    public void load() {
        guildService.load();
        commandService.loadCommands();
    }
}
