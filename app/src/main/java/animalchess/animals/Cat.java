package animalchess.animals;

import animalchess.board.Board;

public class Cat extends Animal {

	// Constructor 
    public Cat (boolean isRed, Board board){
        super(isRed, board);
        this.strength = 2;
        if (isRed == true) {
        	setPosition(1,1);
        }
        else {
        	setPosition(5,7);
        }
    }
    @Override 
    public String toString() {
    	return "Cat";
    }
}
