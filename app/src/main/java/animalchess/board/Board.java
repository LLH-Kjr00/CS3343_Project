package animalchess.board;

import java.util.ArrayList;

import animalchess.animals.Animal;
import animalchess.command.GameCommand;

public class Board {
    // The size of the board is 7 (width) * 9 (height)
    // and take the starting number as 0, at lower left corner
    private Animal[][] animals = new Animal[2][8];
    private boolean is_P1_Turn = true; // Flag to track player 1's turn
	private boolean is_P1_Win = false; // Flag to track player 1's win
	private boolean is_P2_Win = false; // Flag to track player 2's win
    public final int[] rivers_y_range = {3,6};
    public final int[] left_river_x_range = {1,3};
    public final int[] right_river_x_range = {4,6};
    private ArrayList<GameCommand> commands = new ArrayList<GameCommand>();

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
    public boolean isOccupiedByFriendly(int x, int y, String owner) {
        //check if friendly den
        if (owner == "R")
            if (x == 3 && y == 0)
                return true;
        else
            if (x == 3 && y == 7)
                return false;

        //check if friendly animal
        Animal target = animals[x][y];
        if (target != null && target.get_Owner() == owner)
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
    
    public void store_and_execute(GameCommand cmd) {
		this.commands.add(cmd);
		cmd.execute();
	} 
}