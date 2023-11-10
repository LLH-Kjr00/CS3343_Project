package animalchess.exceptions;

import java.lang.reflect.Method;

public class InvalidEventListenerParameterException extends RuntimeException {
    public InvalidEventListenerParameterException(Class<?> objectClass, Method method) {
        super("Invalid parameter type for event handler method " + method.getName() + " in class " + objectClass.getName() + ". Expected parameter type: (Event)");
    }
}
