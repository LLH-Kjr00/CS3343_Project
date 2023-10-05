package cs3343.animalchess.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, String id) {
        super(String.format("Entity %s not found in %s entity repository", id, entityName));
    }

}
