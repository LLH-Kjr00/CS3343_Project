package animalchess.exceptions;

public class InvalidServiceConstructorException extends RuntimeException {
    public InvalidServiceConstructorException(Class<?> serviceClass) {
        super("Invalid constructor for service " + serviceClass.getName() + ". Service must have a single constructor.");
    }
}
