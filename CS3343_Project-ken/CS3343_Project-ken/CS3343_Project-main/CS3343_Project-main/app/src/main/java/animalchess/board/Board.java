package animalchess.board;

import java.util.Collection;

import animalchess.animals.Animal;

public class Board {
    // The size of the board is 7 (width) * 9 (height)
    // and take the starting number as 0, at lower left corner
    private final int[] rivers_y_range = {3,6};
    private final int[] left_river_x_range = {1,3};
    private final int[] right_river_x_range = {4,6};
    
    //private final Collection<Animal>redAnimal;
    //private final Collection<Animal>blueAnimal;
    //private final RedPlayer redPlayer;
    //private final BluePlayer bluePlayer;
    //private final Player currentPlayer;
    //private final Move transitionMove;

    //public Board(){};

    public void addAnimal2Board(Animal piece, int x, int y){

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
        return true;
    }
}