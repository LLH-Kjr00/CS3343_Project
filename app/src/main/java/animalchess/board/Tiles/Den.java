package animalchess.board.Tiles;

import animalchess.animals.Animal;

public class Den extends Tile{
    public Den (boolean isRed) {
        super(isRed);
    }

    @Override
    public boolean isFriendlyDen(boolean isRed) {
        if (isRed == this.getIsRed())
            return true;
        else
            return false;
    }

    @Override
    public void setAnimal(Animal occupier) {
        //Event.declareWinner(!getIsRed());
    }
    @Override 
    public String toString() {
		return "Den";
    	
    }
}
