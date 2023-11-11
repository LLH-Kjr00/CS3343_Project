package animalchess.animals;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class Elephant extends Animal {
	public Elephant (boolean isRed, Board board){
        super(isRed, board);
        this.strength = 8;
        
        if (isRed == true) {
        	setPosition(0,2);
        }
        else {
        	setPosition(6,6);
        }
    }
	@Override
    public void Eat(Animal victim) throws InvalidMovementException{
        if (victim.strength == 1)
        	throw new InvalidMovementException("you cannot eat mouse as an Elephant.");
        else
            super.Eat(victim);
    }
	@Override 
    public String toString() {
    	return "Elephant";
    }
}
