package com.lfonta.littleowl.entities;

import lombok.Builder;
import com.lfonta.littleowl.types.enums.LocaleType;

@Builder
public record GuildSettings (
        LocaleType locale) {}
