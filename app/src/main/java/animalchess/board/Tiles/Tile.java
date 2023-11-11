package animalchess.board.Tiles;

import animalchess.animals.Animal;

public class Tile {

    protected Animal occupier;
    private boolean isRed;


    public Tile(boolean isRed) {
        this.isRed = isRed;
    }

    public void setAnimal(Animal occupier) {
        this.occupier = occupier;
    }

    public boolean isWater() {
        return false;
    }
    
    public boolean isFriendlyDen(boolean isRed) {
        return false;
    }

    public Animal getAnimal() {
        return occupier;
    }

    public void removeAnimal() {
        occupier = null;
    }

    public boolean getIsRed() {
        return isRed;
    }
    @Override 
    public String toString() {
		return "Normal Tile";
    	
    }

}
