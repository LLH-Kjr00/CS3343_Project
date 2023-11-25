package animalchess.animals;

import animalchess.exceptions.InvalidMovementException;

public interface GeneralAnimal_Actions {
    public boolean checkIsValidMove(int destX, int destY) throws InvalidMovementException;
    public void Move (int x, int y) throws InvalidMovementException;
    public void MoveTo (int x, int y) ;
    public void Eat(Animal victim) throws InvalidMovementException;

}
