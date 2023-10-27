package animalchess.animals;


public class Leopard extends Animal {
	public Leopard (boolean isRed){
        super(isRed);
        this.strength = 5;
        if (isRed == true) {
        	setPosition(2,4);
        }
        else {
        	setPosition(6,2);
        }
    }
}