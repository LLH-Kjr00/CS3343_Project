package animalchess.animals;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class Mouse extends Animal implements SubmergingAnimal_Actions{
	// Constructor 
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

    // New rule: Mouse can go in water
    // ignore isInWater() exception
    @Override
    public boolean checkIsValidMove(int destX, int destY) throws InvalidMovementException {
    	if (Math.abs(x-destX) + Math.abs(y-destY) > 1) {
    		throw new InvalidMovementException("you cannot move more than one block.");
        }
        if ((Math.abs(x-destX) + Math.abs(y-destY)) == 0) {
            throw new InvalidMovementException("you cannot move into origin location.");
        }
        if (destX < 0 || destY < 0 || destX > 6 || destY > 8) {
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

	public void Move (int destX, int destY) throws InvalidMovementException{
    	Animal target = null;
        if (checkIsValidMove(destX, destY) == true) {
        	target = board.getTarget(destX, destY);
        	if (target != null && target.isRed != this.isRed) {
        		checkIsEating_inDifferentTerrain(target);
        	}
        }
        super.MoveTo(destX ,destY);

    }
    
    @Override
    public void checkIsEating_inDifferentTerrain(Animal target) throws InvalidMovementException {
		if (board.isInWater(x, y) == board.isInWater(target.x, target.y)) {
		    this.Eat(target);
		} else {
		    throw new InvalidMovementException("you cannot not kill animal from different terrain.");
		}
	}

    //additional rule: Mouse can eat elephant
    @Override
    public void Eat(Animal victim) throws InvalidMovementException{
        if (victim.strength==8) {
            board.removeAnimal(victim.x, victim.y);
            if (victim.get_isRed() == true) {
                board.lower_redCount();
            } else {
                board.lower_blackCount();
            }
        }
        else {
            super.Eat(victim);
        }
        board.check_killAll_Win();
    }
    @Override
    public String toString() {
    	return "Mouse";
    }
}