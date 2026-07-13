package online.theowlery.exceptions;

import lombok.Getter;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import online.theowlery.contexts.CommandContext;
import online.theowlery.types.enums.LocaleType;

import java.util.Map;

public class BotException extends RuntimeException {

    @Getter
    private final CommandInteraction interaction;
    @Getter
    private final String messageKey;
    @Getter
    private final LocaleType localeType;
    @Getter
    private final Map<String, Object> messageParams;
    @Getter
    private final boolean userFacing;

    public BotException(String message) {
        this(message, null);
    }

    public BotException(String message, Throwable cause) {
        super(message, cause);
        this.interaction = null;
        this.messageKey = "error.unknown_error";
        this.localeType = LocaleType.EN;
        this.messageParams = Map.of();
        this.userFacing = false;
    }

    public BotException(CommandContext context, String messageKey) {
        this(context, messageKey, Map.of());
    }

    public BotException(CommandInteraction interaction, String messageKey) {
        this(interaction, messageKey, Map.of());
    }

    public BotException(CommandContext context, String messageKey, Map<String, Object> messageParams) {
        super(messageKey);
        this.interaction = context.slashCommandInteraction();
        this.messageKey = messageKey;
        this.localeType = context.locale();
        this.messageParams = messageParams;
        this.userFacing = true;
    }

    public BotException(CommandInteraction interaction, String messageKey, Map<String, Object> messageParams) {
        super(messageKey);
        this.interaction = interaction;
        this.messageKey = messageKey;
        this.localeType = LocaleType.EN;
        this.messageParams = messageParams;
        this.userFacing = true;
    }

    public BotException(CommandContext context, String messageKey, Throwable cause) {
        super(messageKey, cause);
        this.interaction = context.slashCommandInteraction();
        this.messageKey = messageKey;
        this.localeType = context.locale();
        this.messageParams = Map.of();
        this.userFacing = true;
    }
}
