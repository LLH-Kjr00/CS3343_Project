package animalchess.game;



import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;

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
		// check upper traps
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
		// check bottom traps
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

	public default boolean isDen(int row, int col) {
		// check upper den
		if (row == 0) {
			if (col == 3) {
				return true;
			} else {
				return false;
			}
		}
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
}
