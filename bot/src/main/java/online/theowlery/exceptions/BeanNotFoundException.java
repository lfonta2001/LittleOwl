package online.theowlery.exceptions;

public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(Class<?> clazz) {
        super("This needed Bean doesn't exist or isn't annotated accordingly: " +  clazz.getSimpleName());
    }
}
