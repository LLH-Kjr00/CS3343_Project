package animalchess.animals;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class Mouse extends Animal {

    public Mouse (boolean isRed){
        super(isRed);
        this.strength = 1;
        if (isRed == true) {
        	setPosition(2,6);
        }
        else {
        	setPosition(6,0);
        }
    }

    //new rule: Mouse can go in water
    @Override
    public void checkIsValidMove(int destX, int destY) throws InvalidMovementException {
    	if (Math.abs(x-destX) > 1 || Math.abs(y-destY) > 1) {
    		throw new InvalidMovementException("Cannot move more than one block");
        }
        if ((Math.abs(x-destX) + Math.abs(y-destY)) == 0) {
            throw new InvalidMovementException("Invalid movement! Cannot move into origin location!");
        }
        if (board.isOutBound(destX, destY)) {
            throw new InvalidMovementException("Cannot move outside of board");
        }
        if (board.isOccupiedByFriendlyAnimal(destX, destY, isRed)) {
            throw new InvalidMovementException("Cannot move into friendly units");
        }
    }

    //additional rule: cannot eat animal from different medium
    @Override
    public void Move (int distX, int distY) throws InvalidMovementException{
        Animal target = board.getTarget(distX, distY);
        if (target != null && target.isRed != this.isRed)
            if (board.isInWater(x, y) == board.isInWater(target.x, target.y)) {
                Eat(target);
            } else {
                throw new InvalidMovementException("Cannot not eat animal from different terrain");
            }
        super.MoveTo(distX ,distY);
    }

    //additional rule: Mouse can eat elephant
    @Override
    public void Eat(Animal victim) throws InvalidMovementException{
        if (victim.strength==8)
            board.removeAnimal(victim.x, victim.y);
        else
            super.Eat(victim);
    }
}