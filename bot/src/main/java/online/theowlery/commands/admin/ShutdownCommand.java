package online.theowlery.commands.admin;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.services.MessageService;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;

@SlashCommand
public class ShutdownCommand implements ISlashCommand {

    private final Dotenv dotenv;
    private final JDA client;

    private final MessageService messageService;

    public ShutdownCommand(Dotenv dotenv, JDA client, MessageService messageService) {
        this.dotenv = dotenv;
        this.client = client;
        this.messageService = messageService;
    }

    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .name("shutdown")
                .description("This commands shuts down the bot, only bot owner can use.")
                .category(CommandCategory.BOT_OWNER)
                .ownerOnly(true)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
        String ownerId = dotenv.get("BOT_OWNER_DISCORD_ID");

        if (ownerId == null) {
            System.out.println("Owner ID not found in environmental variables");
            messageService.sendCommandUnableToExecute(context.slashCommandInteraction());
            return;
        }

        if (context.user().getId().equals(ownerId)) {
            messageService.sendReply(context.slashCommandInteraction(), "Shutting down bot...");
            client.shutdown();
        } else {
            messageService.sendReply(context.slashCommandInteraction(), "You are not the bot owner");
        }
    }
}
