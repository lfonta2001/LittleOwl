package online.theowlery.types.interfaces;

import net.dv8tion.jda.api.entities.User;
import online.theowlery.types.enums.LocaleType;

public interface SharedContext {
    LocaleType locale();

    User interactionsUser();
}
