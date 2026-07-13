package online.theowlery.entities;

import lombok.Builder;
import online.theowlery.types.enums.LocaleType;

@Builder
public record GuildSettings (
        LocaleType locale) {}
