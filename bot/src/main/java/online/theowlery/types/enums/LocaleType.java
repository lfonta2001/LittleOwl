package online.theowlery.types.enums;

public enum LocaleType {
    ES("es"),
    EN("en"),
    FR("fr"),
    PT("pt");

    private final String tag;

    LocaleType(String tag) {
        this.tag = tag;
    }

    public String tag() {
        return tag;
    }

    public static LocaleType fromTag(String tag) {
        if (tag == null || tag.isBlank()) {
            return EN;
        }

        String normalized = tag.toLowerCase();

        if (normalized.contains("-")) {
            normalized = normalized.substring(0, normalized.indexOf("-"));
        }

        for (LocaleType locale : values()) {
            if (locale.tag.equals(normalized)) {
                return locale;
            }
        }

        return EN;
    }
}
