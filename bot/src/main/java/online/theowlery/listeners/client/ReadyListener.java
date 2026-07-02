package online.theowlery.listeners.client;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.core.Loader;
import online.theowlery.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;

@Listener
public class ReadyListener extends ListenerAdapter {

    private final Loader loader;

    public ReadyListener(Loader loader) {
        this.loader = loader;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("Bot running with id: " + event.getJDA().getSelfUser().getId());
        loader.load();
    }
}
