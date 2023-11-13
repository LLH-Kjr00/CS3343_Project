package animalchess.board.Tiles;

import animalchess.animals.Animal;

public class TrapTile extends Tile {
    private boolean isRed;

    public TrapTile (boolean isRed) {
    	super();
        this.isRed = isRed;
    }
    
    public boolean getIsRed() {
        return isRed;
    }
    @Override
    public void setAnimal(Animal occupier) {
        if (occupier.get_isRed() != this.getIsRed())
            occupier.setTrapped(true);
        super.setAnimal(occupier);
    }

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
