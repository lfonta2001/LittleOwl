package com.lfonta.theowlery.descriptors;

import lombok.Builder;
import com.lfonta.theowlery.types.enums.CommandOptionType;

@Builder
public record CommandOptionsDescriptor(
        String id,
        boolean required,
        CommandOptionType type,
        int min,
        int max
) {}

