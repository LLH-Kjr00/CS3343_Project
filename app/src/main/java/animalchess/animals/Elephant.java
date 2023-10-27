package animalchess.animals;

public class Elephant extends Animal {
	public Elephant (boolean isRed){
        super(isRed);
        this.strength = 8;
        
        if (isRed == true) {
        	setPosition(2,0);
        }
        else {
        	setPosition(6,6);
        }
    }
}
