package online.theowlery.exceptions;

public class CircularDependencyException extends BotException {
    public CircularDependencyException(Class<?> clazz) {
        super("Circular dependency found for " + clazz.getSimpleName());
    }
}
