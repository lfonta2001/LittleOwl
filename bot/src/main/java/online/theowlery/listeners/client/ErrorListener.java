package online.theowlery.listeners.client;

import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;

@Listener
public class ErrorListener extends ListenerAdapter {
    @Override
    public void onException(@NotNull ExceptionEvent event) {
        System.out.println("An exception occurred: " + event.getCause());
    }
}
