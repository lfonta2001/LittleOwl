package online.theowlery.commands.utility;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.services.MessageService;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;

@SlashCommand
public class OwlCommand implements SlashCommandContract {

    private final MessageService messageService;

    public OwlCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("owl")
                .category(CommandCategory.UTILITY)
                .cooldown(30)
                .guildOnly(true)
                .longExecution(true)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        messageService.sendReply(context, "command.owl.reply");
    }
}
