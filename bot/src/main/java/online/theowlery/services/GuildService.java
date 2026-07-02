package online.theowlery.services;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import online.theowlery.types.annotations.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuildService {

    @Getter
    private final List<Guild> guilds = new ArrayList<>();

    private final JDA client;

    public GuildService(JDA client) {
        this.client = client;
    }

    public void load() {
        guilds.addAll(client.getGuilds());
    }
}
