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
		if (row == 3 || row == 4 || row == 5) {
			if (col == 1 || col == 2 || col == 4 || col == 5) { return true; }
		}
		return false;
	}

	public default boolean isTrap(int row, int col) {
		// check bottom traps
		if (row == 0) {
			if (col == 2 || col == 4) { return true; }
		} else if (row == 1) {
			if (col == 3) { return true; }
		}

		// check upper traps
		else if (row == 8) {
			if (col == 2 || col == 4) { return true; }
		} else if (row == 7) {
			if (col == 3) { return true; }
		}

		return false;
	}

	public default boolean isP1_Den(int row, int col) {
		// check bottom den
		if (row == 0) {
			if (col == 3) { return true; }
		}

		return false;
	}
	
	public default boolean isP2_Den(int row, int col) {
		// check bottom den
		if (row == 8) {
			if (col == 3) { return true; }
		}

		return false;
	}

	public default void setImage(URL image, JLabel tile) {
		ImageIcon pic = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
		tile.setIcon(pic);
	}
	
	public default void initialP1_Animal(int row, int col, JLabel tile) {
		URL image = null;
		if (row == 0) {
			switch(col) {
			case 0:
				image = getClass().getResource("../assets/red_tiger.png");
				setImage(image, tile);
				break;
			case 6:
				image = getClass().getResource("../assets/red_lion.png");
				setImage(image, tile);
				break;
			}
		}
		else if (row == 1) {
			switch(col) {
			case 1:
				image = getClass().getResource("../assets/red_cat.png");
				setImage(image, tile);
				break;
			case 5:
				image = getClass().getResource("../assets/red_dog.png");
				setImage(image, tile);
				break;
			}
		}
		else if (row == 2) {
			switch(col) {
			case 0:
				image = getClass().getResource("../assets/red_elephant.png");
				setImage(image, tile);
				break;
			case 6:
				image = getClass().getResource("../assets/red_mouse.png");
				setImage(image, tile);
				break;
			case 2:
				image = getClass().getResource("../assets/red_wolf.png");
				setImage(image, tile);
				break;
			case 4:
				image = getClass().getResource("../assets/red_leopard.png");
				setImage(image, tile);
				break;
			}
		}
	}
	
	public default void initialP2_Animal(int row, int col, JLabel tile) {
		URL image = null;
		if (row == 8) {
			switch(col) {
			case 6:
				image = getClass().getResource("../assets/blue_tiger.png");
				setImage(image, tile);
				break;
			case 0:
				image = getClass().getResource("../assets/blue_lion.png");
				setImage(image, tile);
				break;
			}
		}
		else if (row == 7) {
			switch(col) {
			case 5:
				image = getClass().getResource("../assets/blue_cat.png");
				setImage(image, tile);
				break;
			case 1:
				image = getClass().getResource("../assets/blue_dog.png");
				setImage(image, tile);
				break;
			}
		}
		else if (row == 6) {
			switch(col) {
			case 6:
				image = getClass().getResource("../assets/blue_elephant.png");
				setImage(image, tile);
				break;
			case 0:
				image = getClass().getResource("../assets/blue_mouse.png");
				setImage(image, tile);
				break;
			case 4:
				image = getClass().getResource("../assets/blue_wolf.png");
				setImage(image, tile);
				break;
			case 2:
				image = getClass().getResource("../assets/blue_leopard.png");
				setImage(image, tile);
				break;
			}
		}
	}
}
