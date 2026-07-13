package online.theowlery.contexts;

import lombok.Builder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.entities.GuildData;
import online.theowlery.entities.GuildSettings;
import online.theowlery.entities.UserData;
import online.theowlery.types.enums.LocaleType;
import online.theowlery.types.interfaces.SharedContext;

@Builder
public record CommandContext(
        SlashCommandInteraction slashCommandInteraction,
        LocaleType locale,
        CommandDescriptor commandInformation,
        GuildData guildData,
        GuildSettings guildSettings,
        UserData userData
) implements SharedContext {

    public User interactionsUser() {
        return slashCommandInteraction.getUser();
    }
}
