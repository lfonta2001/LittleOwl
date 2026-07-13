package online.theowlery.exceptions;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

public class UnknownCommandException extends BotException {
    public UnknownCommandException(SlashCommandInteraction interaction) {
        super(interaction, "error.unknown_command");
    }
}
