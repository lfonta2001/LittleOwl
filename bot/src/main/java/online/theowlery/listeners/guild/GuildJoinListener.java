package online.theowlery.listeners.guild;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener
public class GuildJoinListener extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(GuildJoinListener.class);

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        logger.info("The bot was added to a new Guild:\nGuild info: \t{}", event.getRawData());
    }
}
