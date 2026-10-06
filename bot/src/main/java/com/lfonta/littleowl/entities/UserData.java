package com.lfonta.littleowl.entities;

import lombok.Builder;
import com.lfonta.littleowl.types.enums.LocaleType;

@Builder
public record UserData(
        String id,
        String username,
        LocaleType locale) {
}
