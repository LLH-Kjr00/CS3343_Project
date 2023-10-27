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
		URL imageURL = null;
		ImageIcon pic = null;
		if (row == 0) {
			switch(col) {
			case 0:
				imageURL = getClass().getResource("../assets/red_tiger.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 6:
				imageURL = getClass().getResource("../assets/red_lion.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			}
		}
		else if (row == 1) {
			switch(col) {
			case 1:
				imageURL = getClass().getResource("../assets/red_cat.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 5:
				imageURL = getClass().getResource("../assets/red_dog.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			}
		}
		else if (row == 2) {
			switch(col) {
			case 0:
				imageURL = getClass().getResource("../assets/red_elephant.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 6:
				imageURL = getClass().getResource("../assets/red_mouse.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 2:
				imageURL = getClass().getResource("../assets/red_wolf.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 4:
				imageURL = getClass().getResource("../assets/red_leopard.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			}
		}
	}
	
	public default void initialP2_Animal(int row, int col, JLabel tile) {
		URL imageURL = null;
		ImageIcon pic = null;
		if (row == 8) {
			switch(col) {
			case 6:
				imageURL = getClass().getResource("../assets/blue_tiger.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 0:
				imageURL = getClass().getResource("../assets/blue_lion.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			}
		}
		else if (row == 7) {
			switch(col) {
			case 5:
				imageURL = getClass().getResource("../assets/blue_cat.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 1:
				imageURL = getClass().getResource("../assets/blue_dog.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			}
		}
		else if (row == 6) {
			switch(col) {
			case 6:
				imageURL = getClass().getResource("../assets/blue_elephant.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 0:
				imageURL = getClass().getResource("../assets/blue_mouse.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 4:
				imageURL = getClass().getResource("../assets/blue_wolf.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			case 2:
				imageURL = getClass().getResource("../assets/blue_leopard.png");
				pic = new ImageIcon(new ImageIcon(imageURL).getImage().getScaledInstance(68, 68, Image.SCALE_SMOOTH));
				tile.setIcon(pic);
				break;
			}
		}
	}
}
