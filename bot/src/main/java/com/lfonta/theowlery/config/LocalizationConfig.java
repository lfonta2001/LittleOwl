package com.lfonta.theowlery.config;

import com.lfonta.theowlery.types.annotations.Configuration;
import com.lfonta.theowlery.types.enums.LocaleType;

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
