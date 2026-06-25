package online.theowlery.listeners;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import online.theowlery.core.CommandHandler;
import online.theowlery.types.annotations.Listener;

@Listener
public class InteractionListener implements EventListener {

    private final CommandHandler commandHandler;

    public InteractionListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof SlashCommandInteractionEvent) {
            System.out.println("Usaron un comando pana rabbit");
            System.out.println(((SlashCommandInteractionEvent) event).getName());
            commandHandler.handle((SlashCommandInteractionEvent) event);
        } else if (event instanceof MessageReceivedEvent) {
            System.out.println("Llego un mensaje aca amistad");
            System.out.println(((MessageReceivedEvent) event).getMessage().getContentRaw());
        }
    }
}
