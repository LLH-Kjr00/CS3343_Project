package animalchess.animals;

import animalchess.board.Board;

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
}
