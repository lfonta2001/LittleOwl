package online.theowlery.listeners.client;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import online.theowlery.types.annotations.Listener;

@Listener
public class ReadyListener implements EventListener {
    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println("Bot corriendo con id: " + ((ReadyEvent) event).getJDA().getSelfUser().getId());
        }
    }
}
