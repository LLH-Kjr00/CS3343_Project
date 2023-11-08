package animalchess.board.Tiles;

import animalchess.animals.Animal;

public class WaterTile extends Tile{

    public WaterTile (boolean isRed) {
        super(isRed);
    }

    @Override
    public boolean isWater() {
        return true;
    }
}
