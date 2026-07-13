package online.theowlery.exceptions;

public class BeanNotFoundException extends BotException {
    public BeanNotFoundException(Class<?> clazz) {
        super("This needed Bean doesn't exist or isn't annotated accordingly: " +  clazz.getSimpleName());
    }
}
