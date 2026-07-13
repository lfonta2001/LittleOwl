package online.theowlery.localization;

import online.theowlery.config.LocalizationConfig;
import online.theowlery.entities.GuildSettings;
import online.theowlery.entities.UserData;
import online.theowlery.types.annotations.Manager;
import online.theowlery.types.enums.LocaleType;

import java.util.Locale;

@Manager
public class LocaleManager {

    private final LocalizationConfig localizationConfig;

    public LocaleManager(LocalizationConfig localizationConfig) {
        this.localizationConfig = localizationConfig;
    }

    public LocaleType resolve(UserData userData,
                              GuildSettings guildSettings,
                              Locale discordLocale) {
        if (userData != null && userData.locale() != null) {
            return userData.locale();
        }

        if (guildSettings != null && guildSettings.locale() != null) {
            return guildSettings.locale();
        }

        LocaleType resolvedDiscordLocale = LocaleType.fromTag(discordLocale.toString());

        if (resolvedDiscordLocale != null) {
            return resolvedDiscordLocale;
        }

        return localizationConfig.defaultLocale();
    }
}
