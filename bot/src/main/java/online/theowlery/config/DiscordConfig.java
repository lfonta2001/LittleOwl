package online.theowlery.config;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.types.annotations.Bean;
import online.theowlery.types.annotations.Configuration;
import online.theowlery.types.annotations.Initialize;

import java.util.List;

@Configuration
public class DiscordConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("bot/src/main/resources")
                .ignoreIfMalformed()
                .load();
    }

    @Bean
    public JDA jda(Dotenv dotenv) {
        String token = dotenv.get("DISCORD_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Missing DISCORD_TOKEN environment variable.");
        }

        return JDABuilder.createDefault(token)
                .enableIntents(IntentsConfig.getIntents())
                .enableCache(IntentsConfig.getCacheFlags())
                .setMemberCachePolicy(IntentsConfig.getMemberCachePolicy())
                .build();
    }

    @Initialize
    public void registerListeners(JDA client, List<ListenerAdapter> listeners) {
        client.addEventListener(listeners.toArray());
    }
}
