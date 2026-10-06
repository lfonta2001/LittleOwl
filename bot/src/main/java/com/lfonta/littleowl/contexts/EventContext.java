package com.lfonta.littleowl.contexts;

import lombok.Builder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;
import com.lfonta.littleowl.entities.GuildData;
import com.lfonta.littleowl.entities.GuildSettings;
import com.lfonta.littleowl.entities.UserData;
import com.lfonta.littleowl.types.enums.LocaleType;
import com.lfonta.littleowl.types.interfaces.SharedContext;

@Builder
public record EventContext(
        Class<? extends Event> eventType,
        Event event,
        Guild guild,
        Member member,
        User user,
        LocaleType locale,
        GuildData guildData,
        GuildSettings guildSettings,
        UserData userData
) implements SharedContext {

    public User interactionsUser() {
        return user;
    }

    public Guild interactionsGuild() {
        return guild;
    }
}
