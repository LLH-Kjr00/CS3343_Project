package animalchess.animals;


public class Cat extends Animal {

    public Cat (boolean isRed){
        super(isRed);
        this.strength = 2;
        if (isRed == true) {
        	setPosition(1,1);
        }
        else {
        	setPosition(7,5);
        }
    }
}
