package online.theowlery.services;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import online.theowlery.contexts.CommandContext;
import online.theowlery.entities.GuildData;
import online.theowlery.entities.GuildSettings;
import online.theowlery.entities.UserData;
import online.theowlery.localization.LocaleManager;
import online.theowlery.types.annotations.Service;
import online.theowlery.types.interfaces.SlashCommandContract;

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

    public CommandContext createCommandContext(SlashCommandInteractionEvent event, SlashCommandContract command) {

        String guildId = null;

        if (event.isFromGuild()) {
            guildId = event.getGuild().getId();
        }

        GuildData guildData = guildService.getGuildData(guildId);
        GuildSettings guildSettings = guildService.getGuildSettings(guildId);
        UserData userData = userService.getUserData(event.getUser().getId());

        return CommandContext.builder()
                .slashCommandInteraction(event.getInteraction())
                .commandInformation(command.getDescriptor())
                .guildData(guildData)
                .guildSettings(guildService.getGuildSettings(guildId))
                .locale(localeManager.resolve(
                        userData,
                        guildSettings,
                        event.getGuildLocale().toLocale()
                ))
                .userData(userData)
                .build();
    }

        return new CommandContext(event, event.getGuild(), event.getMember(), event.getUser(), loc);
    }
}
