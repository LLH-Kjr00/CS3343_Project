package animalchess.animals;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class Tiger extends Animal implements JumpingAnimal_Actions {

	// Constructor 
    public Tiger (boolean isRed, Board board){
        super(isRed,board);
        this.strength = 6;
        if (isRed == true) {
        	setPosition(0,0);
        }
        else {
        	setPosition(6,8);
        }
    }



	// New rule: A Tiger or Lion can jump over water
    @Override
    public boolean checkIsValidMove(int destX, int destY) throws InvalidMovementException{
        if (Math.abs(x-destX) + Math.abs(y-destY) > 1) {
            checkIsValidJump(destX, destY);        }
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
    
    // Check if the jump over the river is valid based on Board.getLineAsArray()
    // If there is an animal in between the starting point and ending point 
    // Change isBlocked to be true and return exception
    // If there is no river tile in between the starting point and ending point 
    // Change isLandJump to be true and return exception
    @Override
    public boolean checkIsValidJump (int xdist, int ydist) throws InvalidMovementException{
        //a diagonal jump
        if (Math.abs(x-xdist) != 0 && Math.abs(y-ydist) != 0) {
            throw new InvalidMovementException("you cannot jump diagonally");
        }
        else {
            //a vertical jump
            int start = y, end = ydist;
            //a horizontal jump
            if (start == end) {
                start = x;
                end = xdist;
            }
            int[] Path = Board.getLineAsArray(start, end);
            //check if it is water in between start and end
            for (int i = 1; i < Path.length-1; i++) {
                boolean isLandJump = false, isBlocked = false;
                if (x == xdist) {
                    //check if block(x, y[i]) is water
                    if (!board.isInWater(x, Path[i])) {
                        isLandJump = true;
                    }
                    //check if block(x, y[i]) has an animal in the way
                    if (board.getTarget(x, Path[i]) != null) {
                        isBlocked = true;
                    }
                } else if (y == ydist) {
                    //check if block(x[i], y) is water
                    if (!board.isInWater(Path[i], y)) {
                        isLandJump = true;
                    }
                    //check if block(x[i], y) has an animal in the way
                    if (board.getTarget(Path[i], y) != null) {
                        isBlocked = true;
                    }
                }
                if (isLandJump)
                    throw new InvalidMovementException("you cannot jump over normal tiles.");
                if (isBlocked)
                    throw new InvalidMovementException("you cannot jump when there is animal in between.");
            }
            return true;
        }

    }
    @Override
    public String toString() {
    	return "Tiger";
    }
}