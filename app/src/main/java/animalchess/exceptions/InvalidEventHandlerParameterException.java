package animalchess.exceptions;

import java.lang.reflect.Method;

public class InvalidEventHandlerParameterException extends RuntimeException {
    public InvalidEventHandlerParameterException(Class<?> objectClass, Method method) {
        super("Invalid parameter type for event handler method " + method.getName() + " in class " + objectClass.getName() + ". Expected parameter type: (Event)");
    }
}
