package com.lfonta.littleowl.services;

import com.lfonta.littleowl.contexts.CommandContext;
import com.lfonta.littleowl.types.annotations.Service;

@Service
public class CooldownService {

    public long getCooldown(CommandContext context) {
        return 10;
    }
}
