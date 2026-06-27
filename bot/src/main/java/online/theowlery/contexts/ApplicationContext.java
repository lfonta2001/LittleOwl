package online.theowlery.contexts;

import lombok.Getter;
import online.theowlery.exceptions.BeanCreationException;
import online.theowlery.exceptions.BeanNotFoundException;
import online.theowlery.exceptions.CircularDependencyException;
import online.theowlery.exceptions.InvalidBeanException;
import online.theowlery.types.annotations.Bean;
import online.theowlery.types.annotations.Component;
import online.theowlery.types.annotations.Configuration;
import online.theowlery.types.annotations.Inject;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public final class ApplicationContext {

    @Getter
    private final Map<Class<?>, Object> beans = new HashMap<>();

    @Getter
    private final Map<Class<?>, Method> factoryBeanMethods = new HashMap<>();

    @Getter
    private final Map<Method, Class<?>> factoryMethodOwners = new HashMap<>();

    @Getter
    private final Set<Class<?>> beanDefinitions = new HashSet<>();

    @Getter
    private final Set<Class<?>> configDefinitions = new HashSet<>();

    private final Set<Class<?>> creating = new HashSet<>();

    private ApplicationContext() {
        Reflections reflections = new Reflections("online.theowlery");

        // Bean methods registration

        Set<Class<?>> configDefined = reflections.getTypesAnnotatedWith(Configuration.class);

        for (Class<?> clazz : configDefined) {
            Arrays.stream(clazz.getMethods())
                    .filter(method -> method.isAnnotationPresent(Bean.class))
                    .forEach(method -> {
                            Class<?> returnType = method.getReturnType();

                            if (returnType.equals(Void.TYPE)) {
                                throw new InvalidBeanException(clazz, "@Bean methods cannot return void.");
                            }

                            if (factoryBeanMethods.containsKey(returnType)) {
                                throw new InvalidBeanException(
                                        clazz,
                                        "Multiple @Bean methods produce the same type: " + returnType.getSimpleName()
                                );
                            }

                            factoryBeanMethods.put(returnType, method);
                            factoryMethodOwners.put(method, clazz);
                    });
        }

        configDefinitions.addAll(configDefined);

        // Components registration

        @SuppressWarnings("unchecked")
        Set<Class<? extends Annotation>> componentsAnnotations = reflections.getTypesAnnotatedWith(Component.class)
                .stream()
                .filter(Class::isAnnotation)
                .map(annotation -> (Class<? extends Annotation>) annotation)
                .collect(Collectors.toSet());

        for (Class<? extends Annotation> annotation : componentsAnnotations) {
            beanDefinitions.addAll(reflections.getTypesAnnotatedWith(annotation));
        }

        beanDefinitions.removeIf(Class::isAnnotation);
        beanDefinitions.removeIf(Class::isInterface);
    }

    private Object createBean(Class<?> type) {
        if (beans.containsKey(type)) {
            return beans.get(type);
        }

        if (factoryBeanMethods.containsKey(type)) {
            return createFactoryBean(type);
        }

        if (beanDefinitions.contains(type)) {
            return createClassBean(type);
        }

        Class<?> implementation = findUniqueImlementation(type);

        if (implementation != null) {
            return createBean(implementation);
        }

        throw new BeanNotFoundException(type);
    }

    private Object createFactoryBean(Class<?> type) {
        if (beans.containsKey(type)) {
            return beans.get(type);
        }

        if (creating.contains(type)) {
            throw new CircularDependencyException(type);
        }

        creating.add(type);

        Method method = factoryBeanMethods.get(type);
        Class<?> ownerClass = factoryMethodOwners.get(method);

        try {
            Object owner = createBean(ownerClass);
            Object[] parameters = resolveParameters(method.getParameters());
            Object instance = method.invoke(owner, parameters);

            if (instance == null) {
                throw new InvalidBeanException(ownerClass, "@Bean method returned null: " + method.getName());
            }

            beans.put(type, instance);
            return instance;
        } catch (Exception e) {
            throw new BeanCreationException(type, e);
        } finally {
            creating.remove(type);
        }
    }

    private Object createClassBean(Class<?> clazz) {
        if (beans.containsKey(clazz)) {
            return beans.get(clazz);
        }

        validateClassBean(clazz);

        if (creating.contains(clazz)) {
            throw new CircularDependencyException(clazz);
        }

        creating.add(clazz);

        try {
            Constructor<?> constructor = selectConstructor(clazz);
            Object[] parameters = resolveParameters(constructor.getParameters());

            Object instance = constructor.newInstance(parameters);
            beans.put(clazz, instance);
            return instance;
        } catch (Exception e) {
            throw new BeanCreationException(clazz, e);
        } finally {
            creating.remove(clazz);
        }
    }

    private void validateClassBean(Class<?> clazz) {
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
    }

    private Constructor<?> selectConstructor(Class<?> clazz) {
        Constructor<?>[] possibleConstructors = clazz.getConstructors();

        List<Constructor<?>> constructors;

        if (possibleConstructors.length > 1) {
            constructors = Arrays.stream(possibleConstructors)
                    .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                    .toList();
        } else {
            constructors = Arrays.stream(possibleConstructors).toList();
        }

        if (constructors.size() != 1) {
            throw new InvalidBeanException(
                    clazz,
                    "Exactly one public constructor must be available for dependency injection, or exactly one constructor must be annotated with @Inject."
            );
        }

        return constructors.getFirst();
    }

    private Object[] resolveParameters(Parameter[] parameters) {
        Object[] resolved = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            resolved[i] = resolveParameter(parameters[i]);
        }

        return resolved;
    }

    private Object resolveParameter(Parameter parameter) {
        Class<?> parameterType = parameter.getType();

        if (Collection.class.isAssignableFrom(parameterType)) {
            return resolveCollectionDependency(parameter);
        }

        return createBean(parameterType);
    }

    private Object resolveCollectionDependency(Parameter parameter) {
        Type genericType = parameter.getParameterizedType();

        if (!(genericType instanceof ParameterizedType parameterizedType)) {
            throw new InvalidBeanException(
                    parameter.getDeclaringExecutable().getDeclaringClass(),
                    "Collection dependencies must declare a generic type."
            );
        }

        Type elementType = parameterizedType.getActualTypeArguments()[0];

        if (!(elementType instanceof Class<?> elementClass)) {
            throw new InvalidBeanException(
                    parameter.getDeclaringExecutable().getDeclaringClass(),
                    "Collection dependency generic type must be a concrete class or interface."
            );
        }

        List<?> beansOfType = getOrCreateBeansOfType(elementClass);

        if (List.class.isAssignableFrom(parameter.getType())) {
            return beansOfType;
        }

        if (Set.class.isAssignableFrom(parameter.getType())) {
            return new LinkedHashSet<>(beansOfType);
        }

        if (Collection.class.equals(parameter.getType())) {
            return beansOfType;
        }

        throw new InvalidBeanException(
                parameter.getDeclaringExecutable().getDeclaringClass(),
                "Unsupported collection dependency type: " + parameter.getType().getSimpleName()
        );
    }

    private Class<?> findUniqueImlementation(Class<?> type) {
        List<Class<?>> candidates = beanDefinitions.stream()
                .filter(type::isAssignableFrom)
                .filter(candidate -> !candidate.equals(type))
                .toList();

        if (candidates.isEmpty()) {
            return null;
        }

        if (candidates.size() > 1) {
            throw new InvalidBeanException(
                    type,
                    "Multiple bean implementations found: " + candidates.stream()
                            .map(Class::getSimpleName)
                            .collect(Collectors.joining(", "))
            );
        }

        return candidates.getFirst();
    }

    private <T> List<T> getOrCreateBeansOfType(Class<T> type) {
        List<T> result = new ArrayList<>();

        for (Class<?> beanDefinition : beanDefinitions) {
            if (type.isAssignableFrom(beanDefinition)) {
                result.add(type.cast(createBean(beanDefinition)));
            }
        }

        for (Class<?> factoryType : factoryBeanMethods.keySet()) {
            if (type.isAssignableFrom(factoryType)) {
                result.add(type.cast(createBean(factoryType)));
            }
        }

        return result;
    }

    public static ApplicationContext create() {
        ApplicationContext ctx = new ApplicationContext();

        Set<Class<?>> configDefinitions = ctx.getConfigDefinitions();

        configDefinitions.forEach(ctx::createBean);

        Set<Class<?>> beanDefinitions = ctx.getBeanDefinitions();

        beanDefinitions.forEach(ctx::createBean);

        return ctx;
    }

    public <T> T getBean(Class<T> clazz) {
        Object bean = createBean(clazz);
        return clazz.cast(bean);
    }

    public <T> List<T> getBeansOfType(Class<T> type) {
        return getOrCreateBeansOfType(type);
    }
}
