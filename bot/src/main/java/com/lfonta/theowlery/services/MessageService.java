package com.lfonta.theowlery.services;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.commands.CommandInteraction;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.types.InteractionResponse;
import com.lfonta.theowlery.types.annotations.Service;
import com.lfonta.theowlery.types.enums.LocaleType;
import com.lfonta.theowlery.types.enums.ResponseState;
import com.lfonta.theowlery.types.interfaces.SharedContext;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageService {

    private static final Duration RESPONSE_TTL = Duration.ofMinutes(15);

    private final Map<String, InteractionResponse> responses = new ConcurrentHashMap<>();

    private final LocalizationService localizationService;

    public MessageService(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    // For persistent messages (Music ones, I guess) persist the message/channel ids and edit message through the API
    // Probably should make a specific class to handle longer living messages like dashboards, music, moderation messages, etc.

    public InteractionResponse register(SlashCommandInteraction interaction) {
        InteractionResponse response = new InteractionResponse(interaction);
        responses.put(interaction.getId(), response);
        return response;
    }

    public void deferReply(InteractionResponse response) {
        if (!response.markDeferred()) return;

        response.getInteraction()
                .deferReply()
                .queue(
                        response::completeHook,
                        response::fail
                );
    }

    private void sendReply(CommandContext context, String key) {
        InteractionResponse response = context.interactionResponse();
        ResponseState state = response.getState().get();
        String content = localizationService.translate(context.locale(), key);

        if (response.markReplied()) {
            response.getInteraction().reply(content).queue();
            return;
        }

        if (state == ResponseState.DEFERRED) {
            response.hookFuture()
                    .thenAccept(hook -> hook.editOriginal(content).queue())
                    .exceptionally(throwable -> {
                        response.fail(throwable);
                        return null;
                    });
            return;
        }

        if (state == ResponseState.REPLIED) {
            response.getInteraction()
                    .getHook()
                    .sendMessage(content)
                    .queue();
        }
    }

    private void sendReply(CommandContext context, String key, Map<String, Object> params) {
        InteractionResponse response = context.interactionResponse();
        ResponseState state = response.getState().get();
        String content = localizationService.translate(context.locale(), key, params);

        if (response.markReplied()) {
            response.getInteraction().reply(content).queue();
            return;
        }

        if (state == ResponseState.DEFERRED) {
            response.hookFuture()
                    .thenAccept(hook -> hook.editOriginal(content).queue())
                    .exceptionally(throwable -> {
                        response.fail(throwable);
                        return null;
                    });
            return;
        }

        if (state == ResponseState.REPLIED) {
            response.getInteraction()
                    .getHook()
                    .sendMessage(content)
                    .queue();
        }
    }

    public void sendEphemeralReply(CommandContext context, String key) {
        String message = localizationService.translate(context.locale(), key);

    }

    public void sendEphemeralReply(CommandContext context, String key, Map<String, Object> params) {
        InteractionResponse response = context.interactionResponse();

        if (response.getState().get() == ResponseState.DEFERRED) {
        }
    }

    public void editOriginal(CommandContext context, String key) {

    }

    public void sendFollowup(CommandContext context, String key) {

    }

    public void sendPrivateMessage(User user, LocaleType locale, String key) {
        user.openPrivateChannel().queue(channel ->
                channel.sendMessage(localizationService.translate(locale, key)).queue());
    }

    public void sendPrivateMessage(User user, String message) {
        user.openPrivateChannel().queue(channel -> channel.sendMessage(message).queue());
    }

    public <C extends SharedContext> void sendWarning(C context, String key, User target) {
        String message = localizationService.translate(context.locale(), key);

        context.interactionsUser()
                .openPrivateChannel()
                .queue(channel -> channel.sendMessage(message).queue());
    }

    public <C extends SharedContext> void sendWarning(C context, String key, User target, Map<String, Object> params) {
        String message = localizationService.translate(context.locale(), key, params);

        target.openPrivateChannel().queue(channel -> channel.sendMessage(message).queue());
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

    public void sendCommandUnableToExecute(CommandContext context) {
        sendReply(context, localizationService.translate(context.locale(), "error.command_could_not_execute"));
    }

}
