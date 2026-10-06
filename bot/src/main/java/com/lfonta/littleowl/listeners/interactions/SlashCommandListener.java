package com.lfonta.littleowl.listeners.interactions;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import com.lfonta.littleowl.core.CommandHandler;
import com.lfonta.littleowl.types.annotations.Listener;
import org.jetbrains.annotations.NotNull;

@Listener
public class SlashCommandListener extends ListenerAdapter {

    private final CommandHandler commandHandler;

    public SlashCommandListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        commandHandler.handle(event);
    }
}
