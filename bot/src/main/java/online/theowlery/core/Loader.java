package online.theowlery.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import online.theowlery.contexts.ApplicationContext;
import online.theowlery.listeners.InteractionListener;
import online.theowlery.listeners.client.ReadyListener;
import online.theowlery.mappers.CommandMapper;
import online.theowlery.services.CommandService;
import online.theowlery.types.Event;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.Component;
import online.theowlery.types.annotations.Service;

import java.util.List;

@Service
public class Loader {

    private final JDA client;
    private final CommandService commandService;

    private final List<EventListener> listeners;

    public Loader(JDA client, CommandService commandService, List<EventListener> listeners) {
        this.client = client;
        this.commandService = commandService;
        this.listeners = listeners;
    }

    public void load() {
        client.addEventListener(listeners);
        commandService.loadCommands();
    }
}
