package animalchess.animals;

import animalchess.exceptions.InvalidMovementException;

public interface SubmergingAnimal_Actions {
	public void checkIsEating_inDifferentTerrain(Animal target) throws InvalidMovementException;
}
