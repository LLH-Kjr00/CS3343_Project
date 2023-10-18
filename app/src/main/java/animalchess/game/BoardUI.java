package animalchess.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class BoardUI extends JPanel  {
	int cols = 7;
	int rows = 9;
	int tileSize = 100;
	public BoardUI  () {
		
		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
		this.setBackground(Color.black);
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (int r = 0; r != rows; r++) {
			for (int c = 0; c != cols; c++) {
				g2d.setColor((c+r)%2 == 0? Color.white:Color.black);
				g2d.fillRect(c*tileSize,r*tileSize,tileSize,tileSize);
				System.out.print("sdsd");
			}
		}
	}

	
}
