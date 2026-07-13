package online.theowlery.localization;

import online.theowlery.types.annotations.Initialize;
import online.theowlery.types.annotations.Repository;
import online.theowlery.types.enums.LocaleType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Repository
public class TranslationRepository {

    private final Map<LocaleType, Properties> translations = new HashMap<>();

    @Initialize
    public void load() {
        for (LocaleType locale : LocaleType.values()) {
            String fileName = "locales/" + locale.tag() + ".properties";

            try (InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream(fileName)) {
                if (inputStream == null) {
                    continue;
                }

                Properties properties = new Properties();
                properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                translations.put(locale, properties);
            } catch (IOException exception) {
                throw new RuntimeException("Failed to load locale file: " + fileName, exception);
            }
        }
    }

    public Optional<String> find(LocaleType locale, String key) {
        Properties properties = translations.get(locale);

        if (properties == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(properties.getProperty(key));
    }
}
