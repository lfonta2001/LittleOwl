package online.theowlery.services;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import online.theowlery.types.annotations.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {

    private final Map<String, InteractionHook> deferredMessages = new HashMap<>();

    public void sendCommandUnableToExecute(SlashCommandInteraction event) {
        event.reply("This command could not be executed, contact guild owner or bot owner.").queue();
    }

    public void deferReply(SlashCommandInteraction interaction) {
        interaction.deferReply().queue();
        deferredMessages.put(interaction.getId(), interaction.getHook());
    }

    public void sendReply(User user, MessageChannel channel, String message) {
        channel.sendMessage(message).mention(user).queue();
    }

    public void sendReply(SlashCommandInteraction interaction, String message) {
        if (deferredMessages.containsKey(interaction.getId())) {
            deferredMessages.get(interaction.getId()).editOriginal(message).queue();
            deferredMessages.remove(interaction.getId());
        } else {
            interaction.reply(message).queue();
        }
    }

    public void sendMessage(User user, String message) {
        user.openPrivateChannel().queue(channel -> channel.sendMessage(message).queue());
    }
}
