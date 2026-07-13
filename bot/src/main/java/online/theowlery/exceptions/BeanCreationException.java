package online.theowlery.exceptions;

public class BeanCreationException extends BotException {
    public BeanCreationException(Class<?> clazz, Exception cause) {
        super("There was an error creating the bean: " + clazz.getSimpleName(), cause);
    }
}
