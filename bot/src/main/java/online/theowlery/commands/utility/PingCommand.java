package online.theowlery.commands.utility;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.services.MessageService;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;

@SlashCommand
public class PingCommand implements SlashCommandContract {

    private final MessageService messageService;

    public PingCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("ping")
                .category(CommandCategory.UTILITY)
                .cooldown(2)
                .guildOnly(false)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
        messageService.sendReply(context, "command.ping.reply");
    }
}
