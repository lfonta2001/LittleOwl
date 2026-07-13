package online.theowlery.listeners.client;

import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener
public class ShutdownListener extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownListener.class);

    @Override
    public void onShutdown(@NotNull ShutdownEvent event) {
        logger.info("Bot is shutting down.");
    }
}
