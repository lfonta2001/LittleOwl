package online.theowlery;

import online.theowlery.contexts.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            logger.info("Starting bot...");
            ApplicationContext appContext = ApplicationContext.create();
            logger.info("Bot started successfully!");
        } catch (Exception e) {
            logger.error("Failed to start bot!", e);
            throw e;
        }
    }
}
