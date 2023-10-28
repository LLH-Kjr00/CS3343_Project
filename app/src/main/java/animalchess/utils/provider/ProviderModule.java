package animalchess.utils.provider;

import animalchess.exceptions.RequiredServiceNotRegisteredException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;

public class ProviderModule {

    private final HashMap<Class<?>, Object> singletons = new HashMap<>();
    private final HashMap<Class<?>, Class<?>> bindings = new HashMap<>();

    private ProviderModule() {}

    public static ProviderModule builder() {
        return new ProviderModule();
    }

    public <T> ProviderModule bind(Class<T> interfaceClass, Class<? extends T> implementationClass) {
        bindings.put(interfaceClass, implementationClass);
        return this;
    }
    public ProviderModule singleton(Object instance) {
        singletons.put(instance.getClass(), instance);
        return this;
    }

    private Object getInstance(Class<?> objectType) {
        Class<?> mappedObjectType = bindings.getOrDefault(objectType, objectType);
        if(singletons.containsKey(mappedObjectType)) {
            return singletons.get(mappedObjectType);
        }
        throw new RequiredServiceNotRegisteredException(objectType);
    }

    public void resolve() {
        singletons.values().forEach(this::injectAll);
    }
    private void injectAll(Object instance) {
        Class<?> objectClass = instance.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> Modifier.isPrivate(field.getModifiers()))
                .filter(field -> field.trySetAccessible() && field.isAnnotationPresent(Inject.class))
                .forEach(field -> {
                    try {
                        field.set(instance, getInstance(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

}
