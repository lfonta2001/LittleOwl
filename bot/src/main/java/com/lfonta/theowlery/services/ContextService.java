package com.lfonta.theowlery.services;

import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.contexts.EventContext;
import com.lfonta.theowlery.entities.GuildData;
import com.lfonta.theowlery.entities.GuildSettings;
import com.lfonta.theowlery.entities.UserData;
import com.lfonta.theowlery.localization.LocaleManager;
import com.lfonta.theowlery.types.InteractionResponse;
import com.lfonta.theowlery.types.annotations.Service;
import com.lfonta.theowlery.types.interfaces.SlashCommandContract;

import java.util.Locale;

@Service
public class ContextService {

    private final GuildService guildService;
    private final UserService userService;
    private final LocaleManager localeManager;

    public ContextService(GuildService guildService, UserService userService, LocaleManager localeManager) {
        this.guildService = guildService;
        this.userService = userService;
        this.localeManager = localeManager;
    }

    public CommandContext createCommandContext(InteractionResponse response, SlashCommandContract command) {

        String guildId = null;
        Locale locale = null;
        SlashCommandInteraction event = response.getInteraction();

        if (event.isFromGuild()) {
            guildId = event.getGuild().getId();
            locale = event.getGuildLocale().toLocale();
        }

        GuildData guildData = guildService.getGuildData(guildId);
        GuildSettings guildSettings = guildService.getGuildSettings(guildId);
        UserData userData = userService.getOrSetUserData(event.getUser());

        return CommandContext.builder()
                .interactionResponse(response)
                .commandInformation(command.getDescriptor())
                .guildData(guildData)
                .guildSettings(guildService.getGuildSettings(guildId))
                .locale(localeManager.resolve(
                        userData,
                        guildSettings,
                        locale
                ))
                .userData(userData)
                .build();
    }

    public EventContext createEventContext(Event event) {

        return EventContext.builder()
                .eventType(event.getClass())
                .event(event)
                .locale(localeManager.resolve(null, null, null))
                //.user()
                .build();
    }
}
