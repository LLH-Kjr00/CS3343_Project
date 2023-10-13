package animalchess.animals;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import animalchess.board.Board;
import animalchess.junglechessUI.JungleChessUI;

public class Cat extends Animal{
	
	private String name;
	private Image sprite;
	private int strength;
	private String resourcePath;
	//Board board
	public Cat(boolean isRed, int row, int col, JungleChessUI ui) {
		super(isRed, col, row,ui);
		this.name = "Cat";
		this.strength = 2;
		this.sprite = setSprite();
	}
	public Image setSprite() {
		if (isRed == true) {
			resourcePath = "../assets/red_cat.png";
		}
		else {
			resourcePath = "../assets/blue_cat.png";
		}
		try {
			
			return ImageIO.read(getClass().getResource(resourcePath)).getScaledInstance(ui.getTileSize(), ui.getTileSize(), BufferedImage.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sprite;
		
	}
	public void paint(Graphics2D g2a) {
    	g2a.drawImage(sprite,xPos,yPos,null);
    }
	
	
	

	

}
