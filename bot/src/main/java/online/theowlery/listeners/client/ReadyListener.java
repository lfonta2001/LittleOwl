package online.theowlery.listeners.client;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.core.Loader;
import online.theowlery.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Listener
public class ReadyListener extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ReadyListener.class);

    private final Loader loader;

    public ReadyListener(Loader loader) {
        this.loader = loader;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        logger.info("Bot running with id: {}", event.getJDA().getSelfUser().getId());
        loader.load();
    }
}
