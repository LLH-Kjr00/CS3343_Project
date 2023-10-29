package animalchess.animals;

import animalchess.board.Board;
import animalchess.command.Eat_command;
import animalchess.command.Move_command;
import animalchess.exceptions.InvalidMovementException;

public class Animal {
    // R: red team, G: green team
    protected boolean isRed;
    protected String owner;
    // coordinate
    protected int x, y;
    // size of the animal
    protected int strength;
    private boolean trapped = false;
    protected Board board;

    public Animal (boolean isRed, Board board){
        this.isRed = isRed;
        this.board= board;
        if (isRed == true) {
        	owner = "Player 1";
        }
        else {
        	owner = "Player 2";
        }
    }
    // rules for general:
    // 1. Cannot move more than one block
    // 2. Cannot move on the spot
    // (a.k.a choose to pick up the pawn and put it back to original posision)
    // 3. Cannot move outside of chess board
    // 4. Cannot enter river
    // 5. Cannot move into friendly animal or trap or Base
    // 6. Cannot attempt to eat larger animal
    public boolean checkIsValidMove(int destX, int destY) throws InvalidMovementException{
        if (Math.abs(x-destX) > 1 || Math.abs(y-destY) > 1) {
            throw new InvalidMovementException("Cannot move more than one block");
        }
        if ((Math.abs(x-destX) + Math.abs(y-destY)) == 0) {
            throw new InvalidMovementException("Invalid movement! Cannot move into origin location!");
        }
        if (board.isOutBound(destX, destY)) {
            throw new InvalidMovementException("Cannot move outside of board");
        }
        if (board.isInWater(destX, destY)) {
            throw new InvalidMovementException("This animal cannot goes into water");
        }
        if (board.isOccupiedByFriendlyAnimal(destX, destY, isRed)) {
            throw new InvalidMovementException("Cannot move into friendly units");
        }
        if (board.isOccupiedByFriendlyDen(destX, destY, isRed)) {
            throw new InvalidMovementException("Cannot enter friendly den");
        }
        return true;

    }
    //board.store_and_execute(new Move_command(this,x,y));
    public void Move (int x, int y) throws InvalidMovementException{
        Animal target = board.getTarget(x, y);
        if (target != null && target.isRed != this.isRed)
        	//board.store_and_execute(new Eat_command(this,target));
        	this.Eat(target);
        MoveTo(x ,y);
    }

    protected void MoveTo (int x, int y) {
        board.removeAnimal(this.x, this.y);
        board.addAnimal(x, y, this);
        setPosition(x,y);

    }

    public void Eat(Animal victim) throws InvalidMovementException{
        if (victim.strength>this.strength && !victim.trapped)
            throw new InvalidMovementException("Cannot eat bigger target");
        else
            board.removeAnimal(victim.x, victim.y);
    }
    public void setPosition (int x, int y) {
		this.x = x;
		this.y = y;
	}
    public int get_xCoordinate() {
    	return x;
    }
    public int get_yCoordinate() {
    	return y;
    }
    public String get_Owner() {
    	return owner;
    }
    public boolean get_isRed() {
    	return isRed;
    }

    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }


}