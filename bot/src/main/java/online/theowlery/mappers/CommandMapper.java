package online.theowlery.mappers;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.*;
import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.descriptors.CommandOptionsDescriptor;
import online.theowlery.descriptors.SubCommandDescriptor;
import online.theowlery.services.LocalizationService;
import online.theowlery.types.annotations.Service;
import online.theowlery.types.enums.CommandOptionType;
import online.theowlery.types.enums.LocaleType;

import java.util.Map;

@Service
public class CommandMapper {

    private final LocalizationService localizationService;

    public CommandMapper(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    public CommandData getCommandData(CommandDescriptor descriptor) {
        String id = descriptor.id();

        String baseKey = "command." + id;

        Map<LocaleType, String> names = localizationService.translateById(baseKey + ".name");
        Map<LocaleType, String> descriptions = localizationService.translateById(baseKey + ".description");

        SlashCommandData command = Commands.slash(names.get(LocaleType.EN), descriptions.get(LocaleType.EN))
                .setNameLocalizations(LocaleMapper.toDiscordLocalizations(names))
                .setDescriptionLocalizations(LocaleMapper.toDiscordLocalizations(descriptions));

        if (descriptor.subcommands() != null && !descriptor.subcommands().isEmpty()) {
            command.addSubcommands(
                    descriptor
                            .subcommands()
                            .stream()
                            .map(data -> getSubCommandData(data, baseKey))
                            .toList());
        }

        if (descriptor.options() != null && !descriptor.options().isEmpty()) {
            command.addOptions(
                    descriptor
                            .options()
                            .stream()
                            .map(data -> getOptionData(data, baseKey))
                            .toList());
        }

        return command;
    }

    private SubcommandData getSubCommandData(SubCommandDescriptor descriptor, String baseKey) {
        Map<LocaleType, String> names = localizationService.translateById(baseKey + ".subcommand." + descriptor.id() + ".name");
        Map<LocaleType, String> descriptions = localizationService.translateById(baseKey + ".subcommand." + descriptor.id() + ".description");

        SubcommandData subcommand = new SubcommandData(
                names.get(LocaleType.EN),
                descriptions.get(LocaleType.EN)
        )
                .setNameLocalizations(LocaleMapper.toDiscordLocalizations(names))
                .setDescriptionLocalizations(LocaleMapper.toDiscordLocalizations(descriptions));

        if (descriptor.options() != null && !descriptor.options().isEmpty()) {
            subcommand.addOptions(
                    descriptor
                            .options()
                            .stream()
                            .map(data -> getOptionData(data, baseKey))
                            .toList());
        }

        return subcommand;
    }

    private OptionData getOptionData(CommandOptionsDescriptor descriptor, String baseKey) {
        Map<LocaleType, String> names = localizationService.translateById(baseKey + ".option." + descriptor.id() + ".name");
        Map<LocaleType, String> descriptions = localizationService.translateById(baseKey + ".option." + descriptor.id() + ".description");

        return new OptionData(
                fromOptionType(descriptor.type()),
                names.get(LocaleType.EN),
                descriptions.get(LocaleType.EN),
                descriptor.required()
                )
                .setNameLocalizations(LocaleMapper.toDiscordLocalizations(names))
                .setDescriptionLocalizations(LocaleMapper.toDiscordLocalizations(descriptions));
    }

    private OptionType fromOptionType(CommandOptionType option) {
        return switch (option) {
            case ATTACHMENT -> OptionType.ATTACHMENT;
            case BOOLEAN -> OptionType.BOOLEAN;
            case CHANNEL -> OptionType.CHANNEL;
            case INTEGER -> OptionType.INTEGER;
            case MENTIONABLE -> OptionType.MENTIONABLE;
            case NUMBER -> OptionType.NUMBER;
            case ROLE -> OptionType.ROLE;
            case STRING -> OptionType.STRING;
            case USER -> OptionType.USER;
            case UNKNOWN -> OptionType.UNKNOWN;
        };
    }
}
