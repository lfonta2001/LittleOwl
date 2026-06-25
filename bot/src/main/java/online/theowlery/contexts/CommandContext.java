package online.theowlery.contexts;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Locale;

public record CommandContext(
        SlashCommandInteractionEvent slashCommandInteractionEvent,
        Guild guild, Member member,
        User user,
        Locale locale/*, GuildConfig guildConfig */) {
}
