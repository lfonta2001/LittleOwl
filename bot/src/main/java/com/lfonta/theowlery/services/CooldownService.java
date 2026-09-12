package com.lfonta.theowlery.services;

import com.lfonta.theowlery.contexts.CommandContext;
import com.lfonta.theowlery.types.annotations.Service;

@Service
public class CooldownService {

    public long getCooldown(CommandContext context) {
        return 10;
    }
}
