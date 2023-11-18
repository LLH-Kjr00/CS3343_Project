package animalchess.animals;

import animalchess.exceptions.InvalidMovementException;

public interface SubmergingAnimal_Actions {
	public void checkIsDifferentTerrain(Animal target) throws InvalidMovementException;
}
