package animalchess.board.Tiles;

import animalchess.animals.Animal;

public class Den extends Tile{
    private boolean isRed;

    public Den (boolean isRed) {
        super();
        this.isRed = isRed;
    }
    public boolean getIsRed() {
        return isRed;
    }
    @Override
    public boolean isDen() {
    	return true;
    }
    
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
