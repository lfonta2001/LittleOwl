package online.theowlery.listeners.message;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import online.theowlery.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;

@Listener
public class MessageCreateListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        System.out.println("Llego un mensaje aca amistad");
        System.out.println(((MessageReceivedEvent) event).getMessage().getContentRaw());
    }
}
