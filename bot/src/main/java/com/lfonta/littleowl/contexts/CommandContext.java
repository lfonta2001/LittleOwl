package com.lfonta.littleowl.contexts;

import lombok.Builder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import com.lfonta.littleowl.descriptors.CommandDescriptor;
import com.lfonta.littleowl.entities.GuildData;
import com.lfonta.littleowl.entities.GuildSettings;
import com.lfonta.littleowl.entities.UserData;
import com.lfonta.littleowl.exceptions.RequiredOptionMissingException;
import com.lfonta.littleowl.types.InteractionResponse;
import com.lfonta.littleowl.types.enums.CommandOptionType;
import com.lfonta.littleowl.types.enums.LocaleType;
import com.lfonta.littleowl.types.interfaces.SharedContext;
import org.jetbrains.annotations.NotNull;

@Builder
public record CommandContext(
        InteractionResponse interactionResponse,
        LocaleType locale,
        CommandDescriptor commandInformation,
        GuildData guildData,
        GuildSettings guildSettings,
        UserData userData
) implements SharedContext {

    public User interactionsUser() {
        return interactionResponse.getInteraction().getUser();
    }

    public Guild interactionsGuild() {
        return interactionResponse.getInteraction().getGuild();
    }

    @NotNull
    public String getRequiredOption(String optionId, CommandOptionType type) {
        OptionMapping option = interactionResponse.getInteraction().getOption(optionId);
        if (option == null) throw new RequiredOptionMissingException(interactionResponse.getInteraction(), optionId);
        return option.getAsString();
    }

    public String getOptionalOption(String optionId, CommandOptionType type) {
        OptionMapping option = interactionResponse.getInteraction().getOption(optionId);
        return option == null ? null : option.getAsString();
    }
}
