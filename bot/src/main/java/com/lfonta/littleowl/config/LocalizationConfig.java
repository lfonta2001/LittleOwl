package com.lfonta.littleowl.config;

import com.lfonta.littleowl.types.annotations.Configuration;
import com.lfonta.littleowl.types.enums.LocaleType;

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
