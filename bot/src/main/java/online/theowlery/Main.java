package online.theowlery;

import online.theowlery.contexts.ApplicationContext;
import online.theowlery.core.Bot;

public class Main {

    public static void main(String[] args) {
        ApplicationContext appContext = ApplicationContext.create();
        Bot bot = appContext.getBean(Bot.class);
        bot.start();
    }
}
