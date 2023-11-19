package animalchess.board.Tiles;

import animalchess.animals.Animal;

public class TrapTile extends Tile {
    private boolean isRed;
    
    // Constructor 
    // Trap has a boolean isRed to distinguish themselves 
    // Whether it belongs to Player 1 or Player 2
    public TrapTile (boolean isRed) {
    	super();
        this.isRed = isRed;
    }
    
    public boolean getIsRed() {
        return isRed;
    }
    // Overriding setAnimal() to check whether the animal entered is a friendly 
    // Setting the animal to be trapped (Trapped = true) if it is not friendly 
    @Override
    public void setAnimal(Animal occupier) {
        if (occupier.get_isRed() != this.getIsRed())
            occupier.setTrapped(true);
        super.setAnimal(occupier);
    }

    // Overriding removeAnimal() to check whether the animal entered is a friendly 
    // Setting the animal to be freed (Trapped = false) if it is not friendly
    @Override
    public void removeAnimal() {
        if (occupier.get_isRed() != this.getIsRed())
            occupier.setTrapped(false);
        super.removeAnimal();
    }
    
    @Override 
    public String toString() {
		return "Trap";
    	
    }
}
