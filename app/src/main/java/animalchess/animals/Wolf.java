package animalchess.animals;

import animalchess.board.Board;

public class Wolf extends Animal {
	
	// Constructor 
	public Wolf (boolean isRed){
        super(isRed);
        this.strength = 4;
        if (isRed == true) {
        	setPosition(2,2);
        }
        else {
        	setPosition(4,6);
        }
    }
	@Override 
    public String toString() {
    	return "Wolf";
    }
}
