package animalchess.board.Tiles;

import animalchess.animals.Animal;

public class TrapTile extends Tile {

    public TrapTile (boolean isRed) {
        super(isRed);
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
}
