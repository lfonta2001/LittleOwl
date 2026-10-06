package com.lfonta.littleowl.services;

import net.dv8tion.jda.api.entities.User;
import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.types.annotations.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ModerationService {

    private final MessageService messageService;
    private final UserService userService;

    public ModerationService(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    public void warn(CommandContext context, String targetId, String reason) {
        Map<String, Object> params = new HashMap<>();
        params.put("GUILD_NAME", context.guildData().name());
        params.put("ISSUER_ID", context.userData().username());
        params.put("REASON", reason);

        User target = userService.getJDAUser(context.guildData().id(), targetId);

        messageService.sendWarning(context, "message.user_warned", target, params);
    }
}
