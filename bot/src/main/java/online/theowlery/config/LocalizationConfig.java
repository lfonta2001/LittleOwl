package online.theowlery.config;

import online.theowlery.types.annotations.Configuration;
import online.theowlery.types.enums.LocaleType;

@Configuration
public class LocalizationConfig {

    private final LocaleType defaultLocale = LocaleType.EN;

    public LocaleType defaultLocale() {
        return defaultLocale;
    }

    public String localesPath() {
        return "locales";
    }
}
