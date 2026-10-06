package com.lfonta.littleowl.descriptors;

import lombok.Builder;
import com.lfonta.littleowl.types.enums.CommandOptionType;

@Builder
public record CommandOptionsDescriptor(
        String id,
        boolean required,
        CommandOptionType type,
        int min,
        int max
) {}

