package animalchess.animals;

import animalchess.exceptions.InvalidMovementException;

public interface JumpingAnimal_Actions {
	public boolean checkIsValidJump (int xdist, int ydist) throws InvalidMovementException;
}
