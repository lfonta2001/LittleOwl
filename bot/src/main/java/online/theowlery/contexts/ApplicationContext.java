package online.theowlery.contexts;

import online.theowlery.exceptions.BeanCreationException;
import online.theowlery.exceptions.BeanNotFoundException;
import online.theowlery.exceptions.CircularDependencyException;
import online.theowlery.exceptions.InvalidBeanException;
import online.theowlery.types.annotations.Bean;
import online.theowlery.types.annotations.Inject;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.*;

public final class ApplicationContext {

    private final Map<Class<?>, Object> beans = new HashMap<>();

    private final Set<Class<?>> beanDefinitions = new HashSet<>();

    private final Set<Class<?>> creating = new HashSet<>();

    private ApplicationContext() {
        Reflections reflections = new Reflections("online.theowlery");

        beanDefinitions.addAll(reflections.getTypesAnnotatedWith(Bean.class));

        beanDefinitions.removeIf(Class::isAnnotation);
    }

    private Object createBean(Class<?> clazz) {
        if (clazz.isInterface()) {
            throw new InvalidBeanException(
                    clazz,
                    "Interfaces cannot be instantiated."
            );
        }

        if (Modifier.isAbstract(clazz.getModifiers())) {
            throw new InvalidBeanException(
                    clazz,
                    "Abstract classes cannot be instantiated."
            );
        }

        if (!beanDefinitions.contains(clazz)) {
            throw new BeanNotFoundException(clazz);
        }

        if (beans.containsKey(clazz)) {
            return beans.get(clazz);
        }

        if (creating.contains(clazz)) {
            throw new CircularDependencyException(clazz);
        }

        creating.add(clazz);

        Constructor<?>[] possibleConstructors = clazz.getConstructors();

        List<Constructor<?>> constructors;

        if (possibleConstructors.length > 1) {
            constructors = Arrays.stream(possibleConstructors).filter(c -> c.isAnnotationPresent(Inject.class)).toList();
        } else {
            constructors = Arrays.stream(possibleConstructors).toList();
        }

        if (constructors.size() != 1) {
            throw new InvalidBeanException(clazz, "Exactly one constructor must be available for dependency injection.");
        }

        Constructor<?> constructor = constructors.getFirst();

        Class<?>[] parameterTypes = constructor.getParameterTypes();

        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = createBean(parameterTypes[i]);
        }

        try {
            Object instance = constructor.newInstance(parameters);
            beans.put(clazz, instance);
            return instance;
        } catch (Exception e) {
            throw new BeanCreationException(clazz, e);
        } finally {
            creating.remove(clazz);
        }
    }

    public static ApplicationContext create() {
        ApplicationContext ctx = new ApplicationContext();

        Set<Class<?>> beanDefinitions = ctx.getBeanDefinitions();

        beanDefinitions.forEach(ctx::createBean);

        return ctx;
    }

    public Set<Class<?>> getBeanDefinitions() {
        return beanDefinitions;
    }

    public <T> T getBean(Class<T> clazz) {
        Object bean = beans.get(clazz);

        if (bean == null) {
            throw new BeanNotFoundException(clazz);
        }

        return clazz.cast(beans.get(clazz));
    }

    public <T> List<T> getBeansOfType(Class<T> type) {
        return beans.values().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }
}
