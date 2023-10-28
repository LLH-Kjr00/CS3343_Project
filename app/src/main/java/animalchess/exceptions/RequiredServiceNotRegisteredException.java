package animalchess.exceptions;

public class RequiredServiceNotRegisteredException extends RuntimeException {
    public RequiredServiceNotRegisteredException(Class<?> serviceClass) {
        super("Required service " + serviceClass.getName() + " is not registered in service provider");
    }
}
