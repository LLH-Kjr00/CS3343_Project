package animalchess.animals;

import animalchess.board.Board;

public class Leopard extends Animal {
	
	// Constructor 
	public Leopard (boolean isRed){
        super(isRed);
        this.strength = 5;
        if (isRed == true) {
        	setPosition(4,2);
        }
        else {
        	setPosition(2,6);
        }
    }
	@Override 
    public String toString() {
    	return "Leopard";
    }
}