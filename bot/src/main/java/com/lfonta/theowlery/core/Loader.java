package com.lfonta.theowlery.core;

import net.dv8tion.jda.api.JDA;
import com.lfonta.theowlery.services.CommandService;
import com.lfonta.theowlery.services.GuildService;
import com.lfonta.theowlery.types.annotations.Service;

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
