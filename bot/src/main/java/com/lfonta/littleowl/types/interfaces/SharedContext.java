package com.lfonta.littleowl.types.interfaces;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import com.lfonta.littleowl.types.enums.LocaleType;

public interface SharedContext {
    LocaleType locale();
    User interactionsUser();
    Guild interactionsGuild();
}
