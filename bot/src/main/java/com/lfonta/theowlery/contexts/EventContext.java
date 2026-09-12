package com.lfonta.theowlery.contexts;

import lombok.Builder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;
import com.lfonta.theowlery.entities.GuildData;
import com.lfonta.theowlery.entities.GuildSettings;
import com.lfonta.theowlery.entities.UserData;
import com.lfonta.theowlery.types.enums.LocaleType;
import com.lfonta.theowlery.types.interfaces.SharedContext;

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
