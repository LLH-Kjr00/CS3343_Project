package animalchess.exceptions;

public class InvalidMovementException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1201835004770811011L;

	public InvalidMovementException(String message) {
        super(message);
    }
}