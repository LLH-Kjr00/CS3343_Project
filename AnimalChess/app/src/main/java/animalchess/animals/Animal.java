package animalchess.animals;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;
import animalchess.junglechessUI.JungleChessUI;

public abstract class Animal {
    // T: red team, F: blue team
    protected boolean isRed;
    // coordinate in JungleChessUI
    protected int xPos;
	protected int yPos;
    // coordinate in board 
    private int col, row;
    
    
    
    private boolean trapped = false;
    //protected Board board;
	protected JungleChessUI ui;

	
    public Animal (boolean isRed, int row, int col, JungleChessUI ui){
        this.isRed = isRed;
        this.xPos = col*ui.getTileSize();
        this.yPos = row*ui.getTileSize();
        this.col = col;
        this.row = row;
        //this.board = board;
        this.ui = ui;
        
        //board.addAnimal2Board(this, x, y);
    }
    // rules for general:
    // 1. Cannot move more than one block
    // 2. Cannot move on the spot
    // (a.k.a choose to pick up the pawn and put it back to original posision)
    // 3. Cannot move outside of chess board
    // 4. Cannot enter river
    // 5. Cannot move into friendly animal or trap or Base
    // 6. Cannot attempt to eat larger animal
    
    //public void checkIsValidMove(int xdist, int ydist) throws InvalidMovementException{
    //    if ((Math.abs(x-xdist) + Math.abs(y-ydist)) > 1) {
    //        throw new InvalidMovementException("Cannot move more than one block");
    //   }
    //    if ((Math.abs(x-xdist) + Math.abs(y-ydist)) == 0) {
    //        throw new InvalidMovementException("Invalid movement! Cannot move into origin location!");
    //    }
    //    if (board.isOutBound(xdist, ydist)) {
    //        throw new InvalidMovementException("Cannot move outside of board");
    //    }
    //   if (board.isInWater(xdist, ydist)) {
    //        throw new InvalidMovementException("This animal cannot goes into water");
    //   }
    //   if (board.isOccupiedByFriendly(xdist, ydist, color)) {
    //        throw new InvalidMovementException("Cannot move into friendly units");
    //    }
    //}
    
    public void Move (int x, int y){

    }

    public void Eat(Animal victim){

    }

	public abstract void paint(Graphics2D g2a);
    
    


}