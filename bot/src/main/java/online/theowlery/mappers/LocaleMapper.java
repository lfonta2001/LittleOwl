package online.theowlery.mappers;

import net.dv8tion.jda.api.interactions.DiscordLocale;
import online.theowlery.types.enums.LocaleType;

import java.util.EnumMap;
import java.util.Map;

public final class LocaleMapper {
    public static DiscordLocale toDiscordLocale(LocaleType locale) {
        return switch (locale) {
            case EN -> DiscordLocale.ENGLISH_US;
            case ES -> DiscordLocale.SPANISH;
            case FR -> DiscordLocale.FRENCH;
            case PT -> DiscordLocale.PORTUGUESE_BRAZILIAN;
        };
    }

    public static Map<DiscordLocale, String> toDiscordLocalizations(Map<LocaleType, String> translations) {
        Map<DiscordLocale, String> localizations = new EnumMap<>(DiscordLocale.class);

        for (Map.Entry<LocaleType, String> entry : translations.entrySet()) {
            localizations.put(toDiscordLocale(entry.getKey()), entry.getValue());
        }

        return localizations;
    }
}
