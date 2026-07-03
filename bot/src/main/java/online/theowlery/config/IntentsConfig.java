package online.theowlery.config;

import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.Collection;
import java.util.List;

public final class IntentsConfig {
    private IntentsConfig() {}

    public static Collection<GatewayIntent> getIntents() {
        return List.of(
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MODERATION,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_PRESENCES
        );
    }

    public static Collection<CacheFlag> getCacheFlags() {
        return List.of(CacheFlag.ACTIVITY);
    }

    public static MemberCachePolicy getMemberCachePolicy() {
        return MemberCachePolicy.ONLINE;
    }
}
