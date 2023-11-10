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
import animalchess.board.Tiles.*;

public class Board {
    // The size of the board is 7 (width) * 9 (height)
    // and take the starting number as 0, at lower left corner
    private Tile[][] tiles = new Tile[7][9];

    private boolean is_P1_Turn = true; // Flag to track player 1's turn
	private boolean is_P1_Win = false; // Flag to track player 1's win
	private boolean is_P2_Win = false; // Flag to track player 2's win

    public Board(){
        init();
    }

    public boolean isOutBound(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 9) {
            return true;
        }
        return false;
    }
    public boolean isInWater(int x, int y) {
        return tiles[x][y].isWater();
    }
    public boolean isOccupiedByFriendlyAnimal(int x, int y, boolean isRed) {

        //check if friendly animal
        Animal target = tiles[x][y].getAnimal();
        if (target != null && target.get_isRed() == isRed)
            return true;
        else
            return false;
    }
    public boolean isOccupiedByFriendlyDen(int x, int y, boolean isRed) {
        return tiles[x][y].isFriendlyDen(isRed);
    }

    public Animal getTarget(int x, int y) {
        return tiles[x][y].getAnimal();
    }
    
    public Tile getTile(int x, int y) {
    	return tiles[x][y];
    }

    public void removeAnimal(int x, int y) {
        tiles[x][y].removeAnimal();
    }

    public void addAnimal(int x, int y, Animal animal) {
        tiles[x][y].setAnimal(animal);
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
    
    
    public void init() {

        //map init
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                tiles[i][j] = new Tile(false);
            }
        }

        //river
        for (int j = 3; j < 6; j++) {
            tiles[1][j] = new WaterTile(false);
            tiles[2][j] = new WaterTile(false);
            tiles[4][j] = new WaterTile(false);
            tiles[5][j] = new WaterTile(false);
        }

        //red base
        tiles[2][0] = new TrapTile(true);
        tiles[3][1] = new TrapTile(true);
        tiles[4][0] = new TrapTile(true);
        tiles[3][0] = new Den(true);

        //blue base
        tiles[2][8] = new TrapTile(false);
        tiles[3][7] = new TrapTile(false);
        tiles[4][8] = new TrapTile(false);
        tiles[3][8] = new Den(false);

        //red animal
        tiles[0][0].setAnimal(new Tiger(true,this));
        tiles[6][0].setAnimal(new Lion(true,this));
        tiles[1][1].setAnimal(new Cat(true,this));
        tiles[5][1].setAnimal(new Dog(true,this));
        tiles[0][2].setAnimal(new Elephant(true,this));
        tiles[2][2].setAnimal(new Wolf(true,this));
        tiles[4][2].setAnimal(new Leopard(true,this));
        tiles[6][2].setAnimal(new Mouse(true,this));

        //blue animal
        tiles[0][6].setAnimal(new Mouse(false,this));
        tiles[2][6].setAnimal(new Leopard(false,this));
        tiles[4][6].setAnimal(new Wolf(false,this));
        tiles[6][6].setAnimal(new Elephant(false,this));
        tiles[1][7].setAnimal(new Dog(false,this));
        tiles[5][7].setAnimal(new Cat(false,this));
        tiles[0][8].setAnimal(new Lion(false,this));
        tiles[6][8].setAnimal(new Tiger(false,this));

    }


}