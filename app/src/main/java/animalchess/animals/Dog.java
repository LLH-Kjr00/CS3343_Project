package animalchess.animals;

import animalchess.board.Board;

public class Dog extends Animal {
	
	// Constructor 
    public Dog (boolean isRed,Board board){
        super(isRed, board);
        this.strength = 3;
        
        if (isRed == true) {
        	setPosition(5,1);
        }
        else {
        	setPosition(1,7);
        }
    }
    @Override 
    public String toString() {
    	return "Dog";
    }
}