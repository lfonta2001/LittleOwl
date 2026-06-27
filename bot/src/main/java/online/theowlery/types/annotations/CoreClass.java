package online.theowlery.types.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CoreClass {
    // This annotation must be used only on the main classes of a module
    // only the main entry point of a specific utility
}
