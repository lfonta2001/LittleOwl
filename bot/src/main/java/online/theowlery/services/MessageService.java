package online.theowlery.services;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import online.theowlery.contexts.CommandContext;
import online.theowlery.types.annotations.Service;
import online.theowlery.types.enums.LocaleType;
import online.theowlery.types.interfaces.SharedContext;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {

    private final Map<String, InteractionHook> deferredMessages = new HashMap<>();

    private final LocalizationService localizationService;

    public MessageService(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    public void sendCommandUnableToExecute(CommandContext context) {
        context.slashCommandInteraction().reply(localizationService.translate(context.locale(), "error.command_could_not_execute")).queue();
    }

    public void deferReply(SlashCommandInteraction interaction) {
        interaction.deferReply().queue();
        deferredMessages.put(interaction.getId(), interaction.getHook());
    }

    public void sendReply(User user, MessageChannel channel, LocaleType locale, String key) {
        channel.sendMessage(localizationService.translate(locale, key)).mention(user).queue();
    }

    public void sendReply(CommandContext context, String key, Map<String, Object> params) {
        context.slashCommandInteraction().reply(localizationService.translate(context.locale(), key, params)).queue();
    }

    public void sendReply(CommandContext context, String key) {
        SlashCommandInteraction interaction = context.slashCommandInteraction();
        String message = localizationService.translate(context.locale(), key);
        if (deferredMessages.containsKey(interaction.getId())) {
            deferredMessages.get(interaction.getId()).editOriginal(message).queue();
            deferredMessages.remove(interaction.getId());
        } else {
            interaction.reply(message).queue();
        }
    }

    public void sendInteractionError(CommandInteraction interaction, String key, LocaleType locale, Map<String, Object> params) {
        if (interaction == null) return;

        String message = localizationService.translate(locale, key, params);

        String interactionId = interaction.getId();

        if (deferredMessages.containsKey(interactionId)) {
            deferredMessages.get(interactionId).editOriginal(message).queue();
            deferredMessages.remove(interactionId);
            return;
        }
        if (interaction.isAcknowledged()) {
            interaction.getHook().sendMessage(message).setEphemeral(true).queue();
        } else {
            interaction.reply(message).setEphemeral(true).queue();
        }
    }

    public void sendPrivateMessage(User user, LocaleType locale, String key) {
        user.openPrivateChannel().queue(channel ->
                channel.sendMessage(localizationService.translate(locale, key)).queue());
    }

    public void sendPrivateMessage(User user, String message) {
        user.openPrivateChannel().queue(channel -> channel.sendMessage(message).queue());
    }

    public <C extends SharedContext> void sendWarning(C context, String key, Map<String, Object> params) {
        String message = localizationService.translate(context.locale(), key, params);

        context.interactionsUser()
                .openPrivateChannel()
                .queue(channel -> channel.sendMessage(message).queue());
    }
}
