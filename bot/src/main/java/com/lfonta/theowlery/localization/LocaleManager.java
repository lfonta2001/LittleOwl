package com.lfonta.theowlery.localization;

import com.lfonta.theowlery.config.LocalizationConfig;
import com.lfonta.theowlery.entities.GuildSettings;
import com.lfonta.theowlery.entities.UserData;
import com.lfonta.theowlery.types.annotations.Manager;
import com.lfonta.theowlery.types.enums.LocaleType;

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

        if (discordLocale != null) {
            LocaleType resolvedDiscordLocale = LocaleType.fromTag(discordLocale.toString());

            if (resolvedDiscordLocale != null) {
                return resolvedDiscordLocale;
            }
        }

        return localizationConfig.defaultLocale();
    }
}
