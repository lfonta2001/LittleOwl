package online.theowlery.contexts;

import lombok.Builder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.Event;
import online.theowlery.entities.GuildData;
import online.theowlery.entities.GuildSettings;
import online.theowlery.entities.UserData;
import online.theowlery.types.enums.LocaleType;
import online.theowlery.types.interfaces.SharedContext;

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
}
