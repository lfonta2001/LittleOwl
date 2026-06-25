package online.theowlery.services;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import online.theowlery.contexts.CommandContext;
import online.theowlery.types.annotations.Service;

import java.util.Locale;

@Service
public class ContextService {
    public CommandContext createCommandContext(SlashCommandInteractionEvent event) {
        Locale loc;

        if (event.isFromGuild()) {
            loc = event.getGuildLocale().toLocale();
        } else {
            loc = event.getUserLocale().toLocale();
        }

        return new CommandContext(event, event.getGuild(), event.getMember(), event.getUser(), loc);
    }
}
