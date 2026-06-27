package online.theowlery.core;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import online.theowlery.config.IntentsConfig;
import online.theowlery.contexts.ApplicationContext;
import online.theowlery.services.CommandService;
import online.theowlery.types.annotations.CoreClass;

@CoreClass
public class Bot {

    private final CommandService service;
    private final Loader loader;

    public Bot(CommandService service, Loader loader) {
        this.loader = loader;
        this.service = service;
    }

    public void start() {
        service.cleanCommands();
        loader.load();
    }
}
