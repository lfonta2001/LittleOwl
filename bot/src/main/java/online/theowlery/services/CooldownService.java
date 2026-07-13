package online.theowlery.services;

import online.theowlery.contexts.CommandContext;
import online.theowlery.types.annotations.Service;

@Service
public class CooldownService {

    public long getCooldown(CommandContext context) {
        return 10;
    }
}
