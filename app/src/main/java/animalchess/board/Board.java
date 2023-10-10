package animalchess.board;

import animalchess.animals.Animal;

public class Board {
    // The size of the board is 7 (width) * 9 (height)
    // and take the starting number as 0, at lower left corner
    public Animal[][] animals = new Animal[7][9];
    public final int[] rivers_y_range = {3,6};
    public final int[] left_river_x_range = {1,3};
    public final int[] right_river_x_range = {4,6};


    public Board(){};

    public void addAnimal2Board(Animal piece, int x, int y){
        animals[x][y] = piece;
    }
    public Animal getLocationInfo(int x, int y) {
        Animal obs_any = null;

        return obs_any;
    }
    public boolean isOutBound(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 9) {
            return true;
        }
        return false;
    }
    public boolean isInWater(int x, int y) {
        // left river case
        if ((x < left_river_x_range[1] && x > left_river_x_range[0]) && ( y < rivers_y_range[1] && y > rivers_y_range[0])){
            return true;
        }
        // left river case
        else if ((x < right_river_x_range[1] && x > right_river_x_range[0]) && ( y < rivers_y_range[1] && y > rivers_y_range[0])){
            return true;
        }
        return false;
    }
    public Animal isOccupiedByFriendly(int x, int y, String col) {
        //check if friendly den
        if (col == "R")
            if (x == 3 && y == 0)
                return true;
        else
            if (x == 3 && y == 7)
                return false;

        //check if friendly animal
        Animal target = animals[x][y];
        if (target != null && target.color == col)
            return true;
        else
            return false;
    }

    public void removeAnimal(int x, int y) {
        animals[x][y] = null;
    }

}