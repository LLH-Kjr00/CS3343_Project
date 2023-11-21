package animalchess.animals;

import animalchess.board.Board;

import animalchess.exceptions.InvalidMovementException;

public abstract class Animal implements GeneralAnimal_Actions{
    // R: red team, G: green team
    protected boolean isRed;
    private String owner;
    // coordinate
    protected int x, y;
    // strength of the animal
    // Deciding whether it is strong enough to eat enemy
    protected int strength;
    private boolean trapped = false;
    protected Board board;

    //Constructor
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
    // Checking is the current move a valid move
    // Throw exceptions and show in logArea if the move is invalid
    
    // rules for moving:
    // Animal itself:
    // 1. Cannot move more than one block
    // 2. Cannot move on the spot
    // (a.k.a choose to pick up the pawn and put it back to original position)
    // 3. Cannot move outside of chess board
    // Board-related:
    // 1. Cannot enter river
    // 2. Cannot move into a friendly animal
    // 3. Cannot move into friendly Den
    @Override
    public boolean checkIsValidMove(int destX, int destY) throws InvalidMovementException{
    	if (Math.abs(x-destX) + Math.abs(y-destY) > 1) {
            throw new InvalidMovementException("you cannot move more than one block.");
        }
        if ((Math.abs(x-destX) + Math.abs(y-destY)) == 0) {
            throw new InvalidMovementException("you cannot move into origin location.");
        }
        if (destX < 0 || destY < 0 || destX > 6 || destY > 8) {
        	throw new InvalidMovementException("you cannot move outside of board.");
        }
        if (board.isInWater(destX, destY)) {
            throw new InvalidMovementException("this animal cannot go into water.");
        }
        if (board.isOccupiedByFriendlyAnimal(destX, destY, isRed)) {
            throw new InvalidMovementException("you cannot move into friendly units.");
        }
        if (board.isOccupiedByFriendlyDen(destX, destY, isRed)) {
            throw new InvalidMovementException("you cannot enter friendly den.");
        }
        return true;

    }
    
    // Check if the destination has a enemy pawn
    // Execute Eat() if there is an enemy pawn
    // Otherwise, execute MoveTo()
    @Override
    public void Move (int destX, int destY) throws InvalidMovementException{
        Animal target = null;
        	if (checkIsValidMove(destX, destY) == true) {
        		target = board.getTarget(destX, destY);
        		if (target != null && target.isRed != this.isRed)
                	//board.store_and_execute(new Eat_command(this,target));
                	this.Eat(target);
                MoveTo(destX ,destY);
        	}
        	
        	
        
        //if (target != null && target.isRed != this.isRed)
        //	//board.store_and_execute(new Eat_command(this,target));
        //	this.Eat(target);
        //MoveTo(x ,y);
        
    }
    // Moving the animal on the board by removing and adding it back to the board
    // Update the animal's position
    // See if the target location is a Den
    @Override
    public void MoveTo (int x, int y) {
        board.removeAnimal(this.x, this.y);
        board.addAnimal(x, y, this);
        setPosition(x,y);
        board.check_atDen(this,x,y);
    }
    
    // Decide whether to eat enemy's pawn
    // Rules for eating in general
    // 1. Cannot attempt to eat enemy's pawn with greater strength
    // (Except when the animal to be eaten is trapped)
    @Override
    public void Eat(Animal victim) throws InvalidMovementException{
        if (victim.strength>this.strength && !victim.trapped)
            throw new InvalidMovementException("you cannot eat animal with more strength than yours.");
        else
        	if (victim.get_isRed() == true) {
                board.lower_redCount();
            } else {
                board.lower_blackCount();
            }
            board.removeAnimal(victim.x, victim.y);
            board.check_killAll_Win();
    }
    
    // Setter function for updating coordinate on the board 
    public void setPosition (int x, int y) {
		this.x = x;
		this.y = y;
	}
    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }
    
    // Getter functions 
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

    


}