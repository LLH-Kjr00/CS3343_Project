package animalchess.game;



import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

interface TileUtil {
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
	
	public default void initialP1_Animal(int row, int col, JLabel tile) {
		if (row == 0) {
			switch (col) {
				case 0 -> tile.setIcon(GameAssets.ICON_RED_TIGER.getImageIcon());
				case 6 -> tile.setIcon(GameAssets.ICON_RED_LION.getImageIcon());
			}
		}
		else if (row == 1) {
			switch (col) {
				case 1 -> tile.setIcon(GameAssets.ICON_RED_CAT.getImageIcon());
				case 5 -> tile.setIcon(GameAssets.ICON_RED_DOG.getImageIcon());
			}
		}
		else if (row == 2) {
			switch (col) {
				case 0 -> tile.setIcon(GameAssets.ICON_RED_ELEPHANT.getImageIcon());
				case 6 -> tile.setIcon(GameAssets.ICON_RED_MOUSE.getImageIcon());
				case 2 -> tile.setIcon(GameAssets.ICON_RED_WOLF.getImageIcon());
				case 4 -> tile.setIcon(GameAssets.ICON_RED_LEOPARD.getImageIcon());
			}
		}
	}
	
	public default void initialP2_Animal(int row, int col, JLabel tile) {
		if (row == 8) {
			switch (col) {
				case 6 -> tile.setIcon(GameAssets.ICON_BLUE_TIGER.getImageIcon());
				case 0 -> tile.setIcon(GameAssets.ICON_BLUE_LION.getImageIcon());
			}
		}
		else if (row == 7) {
			switch (col) {
				case 5 -> tile.setIcon(GameAssets.ICON_BLUE_CAT.getImageIcon());
				case 1 -> tile.setIcon(GameAssets.ICON_BLUE_DOG.getImageIcon());
			}
		}
		else if (row == 6) {
			switch (col) {
				case 6 -> tile.setIcon(GameAssets.ICON_BLUE_ELEPHANT.getImageIcon());
				case 0 -> tile.setIcon(GameAssets.ICON_BLUE_MOUSE.getImageIcon());
				case 4 -> tile.setIcon(GameAssets.ICON_BLUE_WOLF.getImageIcon());
				case 2 -> tile.setIcon(GameAssets.ICON_BLUE_LEOPARD.getImageIcon());
			}
		}
	}
}
