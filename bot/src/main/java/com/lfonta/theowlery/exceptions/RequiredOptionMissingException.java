package com.lfonta.theowlery.exceptions;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.util.Map;

public class RequiredOptionMissingException extends BotException {
    public RequiredOptionMissingException(SlashCommandInteraction interaction, String optionId) {
        super(interaction, "error.missing_required_option", Map.of("OPTION_ID", optionId));
    }
}
