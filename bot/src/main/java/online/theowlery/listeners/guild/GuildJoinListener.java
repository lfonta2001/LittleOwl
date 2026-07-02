package online.theowlery.listeners.guild;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;

@Listener
public class GuildJoinListener extends ListenerAdapter {

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        System.out.println("The bot was added to a new Guild: " + event.getRawData());
    }
}
