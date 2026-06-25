package online.theowlery.listeners.client;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import online.theowlery.types.annotations.Listener;

@Listener
public class ErrorListener implements EventListener {
    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println("Hubo un error iniciando el bot, revisa el log kpo.");
        }
    }
}
