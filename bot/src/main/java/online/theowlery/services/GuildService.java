package online.theowlery.services;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import online.theowlery.entities.GuildData;
import online.theowlery.entities.GuildSettings;
import online.theowlery.repositories.GuildRepository;
import online.theowlery.types.annotations.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GuildService {

    private final GuildRepository guildRepository;

    @Getter
    private final Map<String, Guild> guilds = new HashMap<>();

    private final JDA client;

    public GuildService(JDA client, GuildRepository guildRepository) {
        this.client = client;
        this.guildRepository = guildRepository;
    }

    public void load() {
        for (Guild guild : client.getGuilds()) {
            guilds.put(guild.getId(), guild);
        }
    }

    public GuildData getGuildData(String guildId) {
        if (!guilds.containsKey(guildId)) return null;
        return GuildData.builder()
                .id(guildId)
                .name(guilds.get(guildId).getName())
                .build();
    }

    public GuildSettings getGuildSettings(String guildId) {
        return GuildSettings.builder().build();
    }
}
