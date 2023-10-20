package animalchess.animals;

import animalchess.board.Board;
import animalchess.command.Eat_command;
import animalchess.command.Move_command;
import animalchess.exceptions.InvalidMovementException;

public class Animal {
    // R: red team, G: green team
    public String owner;
    // coordinate
    protected int x, y;
    // size of the animal
    protected int weight;
    private boolean trapped = false;
    protected Board board;

    public Animal (String owner, int xCoordinate, int yCoordinate, int weight, Board board){
        this.owner = owner;
        this.x = xCoordinate;
        this.y = yCoordinate;
        this.weight = weight;
        this.board = board;

        board.addAnimal2Board(this, x, y);
    }
    // rules for general:
    // 1. Cannot move more than one block
    // 2. Cannot move on the spot
    // (a.k.a choose to pick up the pawn and put it back to original posision)
    // 3. Cannot move outside of chess board
    // 4. Cannot enter river
    // 5. Cannot move into friendly animal or trap or Base
    // 6. Cannot attempt to eat larger animal
    public void checkIsValidMove(int xdist, int ydist) throws InvalidMovementException{
        if ((Math.abs(x-xdist) + Math.abs(y-ydist)) > 1) {
            throw new InvalidMovementException("Cannot move more than one block");
        }
        if ((Math.abs(x-xdist) + Math.abs(y-ydist)) == 0) {
            throw new InvalidMovementException("Invalid movement! Cannot move into origin location!");
        }
        if (board.isOutBound(xdist, ydist)) {
            throw new InvalidMovementException("Cannot move outside of board");
        }
        if (board.isInWater(xdist, ydist)) {
            throw new InvalidMovementException("This animal cannot goes into water");
        }
        if (board.isOccupiedByFriendly(xdist, ydist, owner)) {
            throw new InvalidMovementException("Cannot move into friendly units");
        }

    }
    //board.store_and_execute(new Move_command(this,x,y));
    public void Move (int x, int y) throws InvalidMovementException{
        Animal target = board.getTarget(x, y);
        if (target != null && target.owner != this.owner)
        	board.store_and_execute(new Eat_command(this,target));
        MoveTo(x ,y);
    }

    protected void MoveTo (int x, int y) {
        board.removeAnimal(this.x, this.y);
        board.addAnimal2Board(this, x, y);
        this.x = x;
        this.y = y;
        
    }

    public void Eat(Animal victim) throws InvalidMovementException{
        if (victim.weight>this.weight && !victim.trapped)
            throw new InvalidMovementException("Cannot eat bigger target");
        else
            board.removeAnimal(victim.x, victim.y);
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


}