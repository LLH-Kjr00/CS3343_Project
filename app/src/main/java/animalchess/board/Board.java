package animalchess.board;

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
import animalchess.game.GameUI;

public class Board {
    // The size of the board is 7 (width) * 9 (height)
    // and take the starting number as 0, at lower left corner
    private Tile[][] tiles = new Tile[7][9];

    public static boolean is_P1_Turn = true; // Flag to track player 1's turn
    public static boolean is_P1_Win = false; // Flag to track player 1's win
    public static boolean is_P2_Win = false; // Flag to track player 2's win

    private int redAnimal_count = 0;
    private int blackAnimal_count = 0;
    
    private static final Board instance = new Board();

	public static Board getInstance() {
		return instance;
	}
    // constructor 
    private Board() {
        init_board();
        init_animalsCount();
        
    }
    // initialize the animal count for both sides 
    // using this way to initialize the number is to cooperate testing and future needs to include more animal 
	public void init_animalsCount() {
		for (int j =0;j!=9;j++) {
        	for (int i = 0 ;i!= 7;i++) {
        		if (tiles[i][j].getAnimal() != null && tiles[i][j].getAnimal().get_isRed() == true) {
        			redAnimal_count++;
        		}
        		else if (tiles[i][j].getAnimal() != null && tiles[i][j].getAnimal().get_isRed() == false) {
        			blackAnimal_count++;
        		}
        	}
        }
	}

   // layered calling method to call the tile at (x,y) coordinate to check whether it is a WaterTile
    public boolean isInWater(int x, int y) {
        return tiles[x][y].isWater();
    }

    public boolean isOccupiedByFriendlyAnimal(int x, int y, boolean isRed) {

        // check if friendly animal
        Animal target = tiles[x][y].getAnimal();
        if (target != null && target.get_isRed() == isRed)
            return true;
        else
            return false;
    }
    // return boolean value to tell whether the tile at (x,y) coordinates are a friendly den
    // according to the rules, friendly animal cannot enter friendly den
    public boolean isOccupiedByFriendlyDen(int x, int y, boolean isRed) {
    	if (tiles[x][y].isDen() == true) {
    		return ((Den) tiles[x][y]).isFriendlyDen(isRed);
    	}
    	else {
    		return false;
    	}
    }

    public Animal getTarget(int x, int y) {
        return tiles[x][y].getAnimal();
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }
    // removing the animal in the tile 
    public void removeAnimal(int x, int y) {
        tiles[x][y].removeAnimal();
    }
    // adding the animal in the tile 
    public void addAnimal(int x, int y, Animal animal) {
        tiles[x][y].setAnimal(animal);
    }

    // Utils
    // generate an array represent of a straight path, [0] is starting point, [size] is destination
    // used by Tiger and Lion's jump
    public static int[] getLineAsArray(int start, int dist) {
        int size = Math.abs(start - dist) + 1;
        int result[] = new int[size];
        if (dist >= start) {
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
    // initialize the board orientation 
    // creating the animals for both sides 
    public void init_board() {
    	//empty animals
    	redAnimal_count = 0;
    	blackAnimal_count = 0;
    	
    	
    	for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
            	if (tiles[i][j] != null) {
                    tiles[i][j] = null;
            	}
                tiles[i][j] = new Tile();
                tiles[i][j].setAnimal(null);
            }
        }
    	
        // map init
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                tiles[i][j] = new Tile();
            }
        }

        // river
        for (int j = 3; j < 6; j++) {
            tiles[1][j] = new WaterTile();
            tiles[2][j] = new WaterTile();
            tiles[4][j] = new WaterTile();
            tiles[5][j] = new WaterTile();
        }

        // red base
        tiles[2][0] = new TrapTile(true);
        tiles[3][1] = new TrapTile(true);
        tiles[4][0] = new TrapTile(true);
        tiles[3][0] = new Den(true);

        // blue base
        tiles[2][8] = new TrapTile(false);
        tiles[3][7] = new TrapTile(false);
        tiles[4][8] = new TrapTile(false);
        tiles[3][8] = new Den(false);

        // red animal
        tiles[0][0].setAnimal(new Tiger(true));
        tiles[6][0].setAnimal(new Lion(true));
        tiles[1][1].setAnimal(new Cat(true));
        tiles[5][1].setAnimal(new Dog(true));
        tiles[0][2].setAnimal(new Elephant(true));
        tiles[2][2].setAnimal(new Wolf(true));
        tiles[4][2].setAnimal(new Leopard(true));
        tiles[6][2].setAnimal(new Mouse(true));

        // blue animal
        tiles[0][6].setAnimal(new Mouse(false));
        tiles[2][6].setAnimal(new Leopard(false));
        tiles[4][6].setAnimal(new Wolf(false));
        tiles[6][6].setAnimal(new Elephant(false));
        tiles[1][7].setAnimal(new Dog(false));
        tiles[5][7].setAnimal(new Cat(false));
        tiles[0][8].setAnimal(new Lion(false));
        tiles[6][8].setAnimal(new Tiger(false));
        

    }
    // check if either side's animal count has reached 0 
    // if reached, changing the respective boolean 
    
    public void check_killAll_Win() {
   
        if (redAnimal_count == 0) {
            is_P2_Win = true;
        } else if (blackAnimal_count == 0) {
            is_P1_Win = true;
        }
    }

    public void check_atDen(Animal animal, int x, int y) {
    	if ((x == 3 && y == 8) && animal.get_isRed() == true) {
    		is_P1_Win = true;
    	}
    	else if ((x == 3 && y == 0) && animal.get_isRed() == false) {
    		is_P2_Win = true;
    	}
    	

    }
    // return if there is a winner in the game for GameUI to announce who wins  
    
    public boolean getWin() {
    	return is_P1_Win || is_P2_Win;
    }

    // functions for lowering the animal counts 
    public void lower_redCount() {
    	redAnimal_count--;
    }
    public void lower_blackCount() {
    	blackAnimal_count--;
    }
    // changing sides 
    public void change_turn() {
    	//System.out.println("in board changeturn " + is_P1_Turn);
    	is_P1_Turn = !is_P1_Turn;
	}

}