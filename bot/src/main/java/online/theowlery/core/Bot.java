package online.theowlery.core;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import online.theowlery.config.IntentsConfig;
import online.theowlery.contexts.ApplicationContext;

public class Bot {
    private final ApplicationContext applicationContext;

    private final Dotenv dotenv;

    private final JDA client;

    public Bot() {
        applicationContext = ApplicationContext.create();

        dotenv = Dotenv.configure()
                .directory("bot/src/main/resources")
                .ignoreIfMalformed()
                .load();

        client = JDABuilder.createDefault(dotenv.get("DISCORD_TOKEN"))
                .enableIntents(IntentsConfig.getIntents())
                .build();
    }

    public void start() {
        Loader.mainLoad(client, applicationContext);
    }
}
