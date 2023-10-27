package animalchess.animals;


public class Wolf extends Animal {
	public Wolf (boolean isRed){
        super(isRed);
        this.strength = 4;
        if (isRed == true) {
        	setPosition(2,2);
        }
        else {
        	setPosition(6,4);
        }
    }
}
