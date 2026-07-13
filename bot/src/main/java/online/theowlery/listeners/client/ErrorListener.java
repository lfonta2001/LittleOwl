package online.theowlery.listeners.client;

import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.core.ExceptionHandler;
import online.theowlery.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;

@Listener
public class ErrorListener extends ListenerAdapter {

    private final ExceptionHandler exceptionHandler;

    public ErrorListener(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void onException(@NotNull ExceptionEvent event) {
        exceptionHandler.handle(event.getCause());
    }
}
