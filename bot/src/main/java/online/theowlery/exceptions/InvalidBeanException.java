package online.theowlery.exceptions;

public class InvalidBeanException extends BotException {
    public InvalidBeanException(Class<?> clazz, String message) {
        super("Invalid bean: " + clazz.getSimpleName() + "\nReason: " + message);
    }
}
