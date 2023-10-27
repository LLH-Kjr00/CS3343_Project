package animalchess.board;

import java.util.ArrayList;

import animalchess.animals.Animal;
import animalchess.animals.Cat;
import animalchess.animals.Dog;
import animalchess.animals.Elephant;
import animalchess.animals.Leopard;
import animalchess.animals.Lion;
import animalchess.animals.Mouse;
import animalchess.animals.Tiger;
import animalchess.animals.Wolf;
import animalchess.command.GameCommand;

public class Board {
    // The size of the board is 7 (width) * 9 (height)
    // and take the starting number as 0, at lower left corner
    private Animal[][] animals = new Animal[7][9];

    private boolean is_P1_Turn = true; // Flag to track player 1's turn
	private boolean is_P1_Win = false; // Flag to track player 1's win
	private boolean is_P2_Win = false; // Flag to track player 2's win
    public final int[] rivers_y_range = {3,6};
    public final int[] left_river_x_range = {1,3};
    public final int[] right_river_x_range = {4,6};
    private ArrayList<GameCommand> commands = new ArrayList<GameCommand>();

    public Board(){

    	animals[0][0] = new Elephant(true);
		animals[0][1] = new Lion(true);
		animals[0][2] = new Tiger(true);
		animals[0][3] = new Leopard(true);
		animals[0][4] = new Wolf(true);
		animals[0][5] = new Dog(true);
		animals[0][6] = new Cat(true);
		animals[0][7] = new Mouse(true);
		animals[1][0] = new Elephant(false);
		animals[1][1] = new Lion(false);
		animals[1][2] = new Tiger(false);
		animals[1][3] = new Leopard(false);
		animals[1][4] = new Wolf(false);
		animals[1][5] = new Dog(false);
		animals[1][6] = new Cat(false);
		animals[1][7] = new Mouse(false);


    };


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
    public boolean isOccupiedByFriendlyAnimal(int x, int y, boolean isRed) {

        //check if friendly animal
        Animal target = animals[x][y];
        if (target != null && target.get_isRed() == isRed)
            return true;
        else
            return false;
    }
    public boolean isOccupiedByFriendlyDen(int x, int y, boolean isRed) {
    	//check if friendly den
        if (isRed == is_P1_Turn && isRed == true) {
            if ((x == 3 && y == 0))
                return true;
        }
        else if (isRed == is_P1_Turn && isRed == false) {
            if (x == 3 && y == 7)
                return true;
        }
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

    public void store_and_execute(GameCommand cmd) {
		this.commands.add(cmd);
		cmd.execute();
	}


}