package animalchess.board.Tiles;

import animalchess.animals.Animal;

public class Tile {

	// Animal that is in the tile
    protected Animal occupier;
    

	// Constructor
    public Tile() {
    }

    // Setter function for animal
    public void setAnimal(Animal occupier) {
        this.occupier = occupier;
    }

    public boolean isWater() {
        return false;
    }
    
    public boolean isDen() {
        return false;
    }

    public Animal getAnimal() {
        return occupier;
    }

    public void removeAnimal() {
        occupier = null;
    }

   
    @Override 
    public String toString() {
		return "Normal Tile";
    	
    }

}
