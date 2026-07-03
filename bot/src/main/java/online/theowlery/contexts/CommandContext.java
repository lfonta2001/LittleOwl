package online.theowlery.contexts;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.util.Locale;

public record CommandContext(
        SlashCommandInteraction slashCommandInteraction,
        Guild guild, Member member,
        User user,
        Locale locale/*, GuildConfig guildConfig */) {
}
