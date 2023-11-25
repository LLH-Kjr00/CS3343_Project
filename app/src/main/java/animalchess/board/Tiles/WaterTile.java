package animalchess.board.Tiles;

public class WaterTile extends Tile{
	
	// Constructor
    public WaterTile () {
        super();
    }

    @Override
    public boolean isWater() {
        return true;
    }
    @Override 
    public String toString() {
		return "Water Tile";
    	
    }
}
