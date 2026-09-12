package com.lfonta.theowlery.entities;

import lombok.Builder;
import com.lfonta.theowlery.types.enums.LocaleType;

@Builder
public record UserData(
        String id,
        String username,
        LocaleType locale) {
}
