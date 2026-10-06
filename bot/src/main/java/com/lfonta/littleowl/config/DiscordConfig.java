package com.lfonta.littleowl.config;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import com.lfonta.littleowl.types.annotations.Bean;
import com.lfonta.littleowl.types.annotations.Configuration;
import com.lfonta.littleowl.types.annotations.Initialize;

import java.util.List;

@Configuration
public class DiscordConfig {

    @Bean
    public JDA jda() {
        String token = System.getenv("DISCORD_TOKEN");

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
