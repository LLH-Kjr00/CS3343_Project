package animalchess.animals;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class Mouse extends Animal {

    public Mouse (boolean isRed, Board board){
        super(isRed, board);
        this.strength = 1;
        if (isRed == true) {
        	setPosition(6,2);
        }
        else {
        	setPosition(0,6);
        }
    }

    //new rule: Mouse can go in water
    @Override
    public boolean checkIsValidMove(int destX, int destY) throws InvalidMovementException {
    	if (Math.abs(x-destX) + Math.abs(y-destY) > 1) {
    		throw new InvalidMovementException("you cannot move more than one block.");
        }
        if ((Math.abs(x-destX) + Math.abs(y-destY)) == 0) {
            throw new InvalidMovementException("you cannot move into origin location.");
        }
        if (board.isOutBound(destX, destY)) {
            throw new InvalidMovementException("you cannot move outside of board.");
        }
        if (board.isOccupiedByFriendlyAnimal(destX, destY, isRed)) {
            throw new InvalidMovementException("you cannot move into friendly units.");
        }
        if (board.isOccupiedByFriendlyDen(destX, destY, isRed)) {
            throw new InvalidMovementException("you cannot enter friendly den.");
        }
        return true;
    }

    //additional rule: cannot eat animal from different medium
    @Override
    public void Move (int distX, int distY) throws InvalidMovementException{
        Animal target = board.getTarget(distX, distY);
        if (target != null && target.isRed != this.isRed)
            if (board.isInWater(x, y) == board.isInWater(target.x, target.y)) {
                this.Eat(target);
            } else {
                throw new InvalidMovementException("you cannot not kill animal from different terrain.");
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
    @Override
    public String toString() {
    	return "Mouse";
    }
}