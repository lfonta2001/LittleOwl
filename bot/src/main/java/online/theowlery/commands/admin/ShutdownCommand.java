package online.theowlery.commands.admin;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.services.MessageService;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.interfaces.SlashCommandContract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SlashCommand
public class ShutdownCommand implements SlashCommandContract {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownCommand.class);

    private final Dotenv dotenv;
    private final JDA client;

    private final MessageService messageService;

    public ShutdownCommand(Dotenv dotenv, JDA client, MessageService messageService) {
        this.dotenv = dotenv;
        this.client = client;
        this.messageService = messageService;
    }

    @NotNull
    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .id("shutdown")
                .category(CommandCategory.BOT_OWNER)
                .ownerOnly(true)
                .build();
    }

    @Override
    public void execute(CommandContext context) {
        String ownerId = dotenv.get("BOT_OWNER_DISCORD_ID");

        if (ownerId == null) {
            logger.error("Owner ID not found in environmental variables");
            messageService.sendCommandUnableToExecute(context);
            return;
        }

        if (context.userData().id().equals(ownerId)) {
            messageService.sendReply(context, "command.shutdown.success_reply");
            client.shutdown();
        } else {
            messageService.sendReply(context, "command.shutdown.not_owner_reply");
        }
    }
}
