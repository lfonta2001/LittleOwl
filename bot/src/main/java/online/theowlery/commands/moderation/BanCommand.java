package online.theowlery.commands.moderation;

import online.theowlery.contexts.CommandContext;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.types.ISlashCommand;
import online.theowlery.types.annotations.SlashCommand;
import online.theowlery.types.enums.CommandCategory;
import online.theowlery.types.enums.GuildPermissions;

import java.util.Collections;

@SlashCommand
public class BanCommand implements ISlashCommand {

    @Override
    public CommandDescriptor getDescriptor() {
        return CommandDescriptor.builder()
                .name("ban")
                .description("Command used to ban someone")
                .category(CommandCategory.MODERATION)
                .cooldown(30)
                .guildOnly(true)
                .permissions(Collections.singletonList(GuildPermissions.BAN_MEMBERS))
                .build();
    }

    @Override
    public void execute(CommandContext context) {
    }
}
