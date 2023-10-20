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
    public boolean isOccupiedByFriendly(int x, int y, String col) {
        //check if friendly den
        if (col == "R")
            if (x == 3 && y == 0)
                return true;
        else
            if (x == 3 && y == 7)
                return true;

        //check if friendly animal
        Animal target = animals[x][y];
        if (target != null && target.color == col)
            return true;
        else
            return false;
    }

    public Animal getTarget(int x, int y) {
        return animals[x][y];
    }

    public void removeAnimal(int x, int y) {
        animals[x][y] = null;
    }

    public static boolean isTrap(int x, int y, String col) {

        if (col == "R") {
            if (x == 2 && y == 7)
                return true;
            if (x == 4 && y == 7)
                return true;
            if (x == 3 && y == 6)
                return true;
        }
        if (col == "B") {
            if (x == 2 && y == 0)
                return true;
            if (x == 4 && y == 0)
                return true;
            if (x == 3 && y == 1)
                return true;
        }
        return false;
    }
    //check if game has ended, return BW if blue enter red nest, vice versa, Continue if no winners
    public String checkGameEnd() {
        if (getTarget(3, 0) != null) {
            return "BW";
        } else if (getTarget(3, 7) != null) {
            return "RW";
        }
        return ("Continue");
    }

    //Utils
    //generate an array represent of a straight path, [0] is starting point, [size] is destination
    public static int[] getLineAsArray(int start, int dist) {
        int size = Math.abs(start-dist) + 1;
        int result[] = new int[size];
        if (dist>=start) {
            for (int i = 0; i < size; i++) {
                result[i] = start + i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                result[i] = start - i;
            }
        }
        return result;
    }
}