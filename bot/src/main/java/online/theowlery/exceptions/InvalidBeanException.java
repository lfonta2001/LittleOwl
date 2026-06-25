package online.theowlery.exceptions;

public class InvalidBeanException extends RuntimeException {
    public InvalidBeanException(Class<?> clazz, String message) {
        super("Invalid bean: " + clazz.getSimpleName() + "\nReason: " + message);
    }
}
