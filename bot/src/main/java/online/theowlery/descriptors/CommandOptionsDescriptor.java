package online.theowlery.descriptors;

import online.theowlery.types.enums.CommandOptionType;

public record CommandOptionsDescriptor(
        String id,
        boolean required,
        CommandOptionType type,
        int min,
        int max
) {}

