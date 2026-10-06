package com.lfonta.littleowl.services;

import com.lfonta.littleowl.config.LocalizationConfig;
import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.localization.TranslationRepository;
import com.lfonta.littleowl.types.annotations.Service;
import com.lfonta.littleowl.types.enums.LocaleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LocalizationService {

    private static final Logger logger = LoggerFactory.getLogger(LocalizationService.class);
    private static final String MISSING_TRANSLATION_FALLBACK_KEY = "error.missing_translation_key";
    private static final String MISSING_TRANSLATION_FALLBACK_MESSAGE = "A translation error occurred; contact the bot owner.";
    private static final String MISSING_TRANSLATION_FALLBACK_PARAM = "The parameter <PARAM> is missing from command and translation files.";

    private final TranslationRepository translationRepository;
    private final LocalizationConfig localizationConfig;

    public LocalizationService(TranslationRepository translationRepository, LocalizationConfig config) {
        this.translationRepository = translationRepository;
        this.localizationConfig = config;
    }

    public String translate(LocaleType locale, String key) {
        return translationRepository.find(locale, key)
                .or(() -> translationRepository.find(localizationConfig.defaultLocale(), key))
                .orElseGet(() -> getMissingTranslationFallback(locale, key));
    }

    public String translateParam(LocaleType locale, String key, String paramKey) {
        String finalKey = key + ".default." + paramKey.toLowerCase();
        return translationRepository.find(locale, finalKey)
                .or(() -> translationRepository.find(localizationConfig.defaultLocale(), finalKey))
                .orElseGet(() -> {
                    logger.warn("Missing localization param key '{}' for locale '{}' and for default locale", paramKey, locale);
                    return MISSING_TRANSLATION_FALLBACK_PARAM.replace("<PARAM>", paramKey);
                });
    }

    private String getMissingTranslationFallback(LocaleType locale, String missingKey) {
        logger.warn("Missing localization key '{}' for locale '{}' and for default locale", missingKey, locale);

        if (MISSING_TRANSLATION_FALLBACK_KEY.equals(missingKey)) {
            return MISSING_TRANSLATION_FALLBACK_MESSAGE;
        }

        return translationRepository.find(locale, MISSING_TRANSLATION_FALLBACK_KEY)
                .or(() -> translationRepository.find(localizationConfig.defaultLocale(), MISSING_TRANSLATION_FALLBACK_KEY))
                .orElse(MISSING_TRANSLATION_FALLBACK_MESSAGE);
    }

    public String translate(LocaleType locale, String key, Map<String, Object> params) {
        String template = translate(locale, key);

        Pattern pattern = Pattern.compile("<([^>]+)>");
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            String paramName = matcher.group(1);

            Object aux = params.get(paramName);
            String value = Objects.toString(aux, "");

            if (aux == null || value.isEmpty()) {
                value = translateParam(locale, key, paramName);
            }

            template = template.replace("<" + paramName + ">", value);
        }

        return template;
    }

    public String translate(CommandContext context, String key) {
        return translate(context.locale(), key);
    }

    public String translate(CommandContext context, String key, Map<String, Object> params) {
        return translate(context.locale(), key, params);
    }

    public Map<LocaleType, String> translateById(String key) {
        Map<LocaleType, String> translations = new HashMap<>();

        for (LocaleType locale : LocaleType.values()) {
            translations.put(locale, translate(locale, key));
        }

        return translations;
    }
}
