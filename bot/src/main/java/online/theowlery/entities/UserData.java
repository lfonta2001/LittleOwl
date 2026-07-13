package online.theowlery.entities;

import lombok.Builder;
import online.theowlery.types.enums.LocaleType;

@Builder
public record UserData(
        String id,
        String username,
        LocaleType locale) {
}
