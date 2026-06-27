package online.theowlery.descriptors;

import online.theowlery.types.enums.CommandOption;

public record CommandOptionsDescriptor(String name, String description, boolean required, CommandOption type, int min, int max) {}

