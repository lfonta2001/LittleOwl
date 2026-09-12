package com.lfonta.theowlery.entities;

import lombok.Builder;
import com.lfonta.theowlery.types.enums.LocaleType;

@Builder
public record GuildSettings (
        LocaleType locale) {}
