package online.theowlery.services;

import online.theowlery.config.LocalizationConfig;
import online.theowlery.contexts.CommandContext;
import online.theowlery.localization.TranslationRepository;
import online.theowlery.types.annotations.Service;
import online.theowlery.types.enums.LocaleType;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LocalizationService {

    private final TranslationRepository translationRepository;
    private final LocalizationConfig localizationConfig;

    public LocalizationService(TranslationRepository translationRepository, LocalizationConfig config) {
        this.translationRepository = translationRepository;
        this.localizationConfig = config;
    }

    public String translate(LocaleType locale, String key) {
        return translationRepository.find(locale, key)
                .or(() -> translationRepository.find(localizationConfig.defaultLocale(), key))
                .orElse("Missing key: " + key);
    }

    public String translate(LocaleType locale, String key, Map<String, Object> params) {
        String template = translate(locale, key);

        Pattern pattern = Pattern.compile("<([^>]+)>");
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            String paramName = matcher.group(1);
            String value = String.valueOf(params.get(paramName));

            if (value.isEmpty()) {
                value = translate(locale, key + ".default." + paramName.toLowerCase());
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
