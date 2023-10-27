package animalchess.animals;


public class Dog extends Animal {

    public Dog (boolean isRed){
        super(isRed);
        this.strength = 3;
        this.board = board;
        if (isRed == true) {
        	setPosition(1,5);
        }
        else {
        	setPosition(7,1);
        }
    }
}