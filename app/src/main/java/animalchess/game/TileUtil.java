package animalchess.game;

import java.awt.Color;
import javax.swing.JLabel;

interface TileUtil {
	// Deciding whether the tile is a river tile according to its x, y coordinates  
	// If yes, pass true
	// Otherwise, pass false
    public default boolean isRiver(int row, int col) {
        // check both rivers, col 1 and 2 are the left river
        // whereas col 4 and 5 are the right river
        if (row == 3) {
            if (col == 1 || col == 2) {
                return true;
            } else if (col == 4 || col == 5) {
                return true;
            } else {
                return false;
            }
        } else if (row == 4) {
            if (col == 1 || col == 2) {
                return true;
            } else if (col == 4 || col == 5) {
                return true;
            } else {
                return false;
            }
        } else if (row == 5) {
            if (col == 1 || col == 2) {
                return true;
            } else if (col == 4 || col == 5) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    // Deciding whether the tile is a trap tile according to its x, y coordinates  
 	// If yes, pass true
 	// Otherwise, pass false
    public default boolean isTrap(int row, int col) {
        // check bottom traps
        if (row == 0) {
            if (col == 2 || col == 4) {
                return true;
            } else {
                return false;
            }
        } else if (row == 1) {
            if (col == 3) {
                return true;
            } else {
                return false;
            }
        }
        // check upper traps
        else if (row == 8) {
            if (col == 2 || col == 4) {
                return true;
            } else {
                return false;
            }
        } else if (row == 7) {
            if (col == 3) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    // Deciding whether the tile is a den for Player 1 according to its x, y coordinates  
 	// If yes, pass true
 	// Otherwise, pass false
    public default boolean isP1_Den(int row, int col) {
        // check bottom den
        if (row == 0) {
            if (col == 3) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // Deciding whether the tile is a den for Player 2 according to its x, y coordinates  
  	// If yes, pass true
  	// Otherwise, pass false
    public default boolean isP2_Den(int row, int col) {
        // check bottom den
        if (row == 8) {
            if (col == 3) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    // Deciding whether the tile's icon needs to be an image of a Player 1's pawn according to its x, y coordinates
    public default void initialP1_Animal(int row, int col, JLabel tile) {
        if (row == 0) {
            switch (col) {
                case 0 -> tile.setIcon(GameAssets.ICON_RED_TIGER.getImageIcon());
                case 6 -> tile.setIcon(GameAssets.ICON_RED_LION.getImageIcon());
            }
        } else if (row == 1) {
            switch (col) {
                case 1 -> tile.setIcon(GameAssets.ICON_RED_CAT.getImageIcon());
                case 5 -> tile.setIcon(GameAssets.ICON_RED_DOG.getImageIcon());
            }
        } else if (row == 2) {
            switch (col) {
                case 0 -> tile.setIcon(GameAssets.ICON_RED_ELEPHANT.getImageIcon());
                case 6 -> tile.setIcon(GameAssets.ICON_RED_MOUSE.getImageIcon());
                case 2 -> tile.setIcon(GameAssets.ICON_RED_WOLF.getImageIcon());
                case 4 -> tile.setIcon(GameAssets.ICON_RED_LEOPARD.getImageIcon());
            }
        }
    }
    
    // Deciding whether the tile's icon needs to be an image of a Player 2's pawn according to its x, y coordinates
    public default void initialP2_Animal(int row, int col, JLabel tile) {
        if (row == 8) {
            switch (col) {
                case 6 -> tile.setIcon(GameAssets.ICON_BLUE_TIGER.getImageIcon());
                case 0 -> tile.setIcon(GameAssets.ICON_BLUE_LION.getImageIcon());
            }
        } else if (row == 7) {
            switch (col) {
                case 5 -> tile.setIcon(GameAssets.ICON_BLUE_CAT.getImageIcon());
                case 1 -> tile.setIcon(GameAssets.ICON_BLUE_DOG.getImageIcon());
            }
        } else if (row == 6) {
            switch (col) {
                case 6 -> tile.setIcon(GameAssets.ICON_BLUE_ELEPHANT.getImageIcon());
                case 0 -> tile.setIcon(GameAssets.ICON_BLUE_MOUSE.getImageIcon());
                case 4 -> tile.setIcon(GameAssets.ICON_BLUE_WOLF.getImageIcon());
                case 2 -> tile.setIcon(GameAssets.ICON_BLUE_LEOPARD.getImageIcon());
            }
        }
    }
    
    // Setting the color of the passed tile to be cyan
    // Because it is a river tile
    public default void setToRiverColor(JLabel tile) {
        tile.setBackground(Color.cyan);
        tile.setForeground(Color.black);
    }

    // Setting the color of the passed tile to be orange
    // Because it is a trap tile
    public default void setToTrapColor(JLabel tile) {
        tile.setBackground(Color.orange);
        tile.setForeground(Color.black);
    }
    
    // Setting the color of the passed tile to be red with white words
    // Because it is a Player 1's Den
    public default void setToP1_DenColor(JLabel tile) {
        tile.setBackground(Color.red);
        tile.setForeground(Color.white);
    }

    // Setting the color of the passed tile to be blue with white words
    // Because it is a Player 2's Den
    public default void setToP2_DenColor(JLabel tile) {
        tile.setBackground(Color.blue);
        tile.setForeground(Color.white);
    }
    
    // Decide whether to change the color of the tile 
    // Depending on the boolean passed from isRiver() ,isTrap(), isP1_Den() and isP2_Den()
    // If all are false, just paint it white as it goes to the statement setToNormalColor()
    public default  void check_Tile_SpecialProp(int row, int col, JLabel tile) {
		if (isRiver(row, col) == true) {
			setToRiverColor(tile);
		} else if (isTrap(row, col) == true) {
			setToTrapColor(tile);
		} else if (isP1_Den(row, col) == true) {
			setToP1_DenColor(tile);
		} else if (isP2_Den(row, col) == true) {
			setToP2_DenColor(tile);
		} else {
			setToNormalColor(tile);
		}
	}
    
    // Setting the color of the passed tile to be white
    // Because it is just a normal tile
    public default void setToNormalColor(JLabel tile) {
		tile.setBackground(Color.white);
		tile.setForeground(Color.black);
	}
}
