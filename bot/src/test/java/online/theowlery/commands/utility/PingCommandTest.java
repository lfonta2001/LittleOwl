package online.theowlery.commands.utility;

import online.theowlery.descriptors.CommandDescriptor;
import online.theowlery.types.enums.CommandCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PingCommandTest {

    @Test
    void getDescriptorShouldReturnPingCommandData() {
        PingCommand pingCommand = new PingCommand();

        CommandDescriptor descriptor = pingCommand.getDescriptor();

        assertEquals("ping", descriptor.name());
        assertEquals("Command to check if bot is up.", descriptor.description());
        assertEquals(CommandCategory.UTILITY, descriptor.category());
        assertEquals(2, descriptor.cooldown());
        assertFalse(descriptor.guildOnly());
    }
}
