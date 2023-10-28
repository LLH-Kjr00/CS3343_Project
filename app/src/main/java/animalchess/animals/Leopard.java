package animalchess.animals;

import animalchess.board.Board;

public class Leopard extends Animal {
	public Leopard (boolean isRed, Board board){
        super(isRed, board);
        this.strength = 5;
        if (isRed == true) {
        	setPosition(4,2);
        }
        else {
        	setPosition(2,6);
        }
    }
}