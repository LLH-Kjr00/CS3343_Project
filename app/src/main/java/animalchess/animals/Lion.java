package animalchess.animals;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class Lion extends Animal {

    public Lion (boolean isRed){
        super(isRed);
        this.strength = 7;
        if (isRed == true) {
        	setPosition(0,6);
        }
        else {
        	setPosition(8,0);
        }
    }
    //New rule: A Tiger or Lion can jump over water
    @Override
    public void checkIsValidMove(int destX, int destY) throws InvalidMovementException{
        if (Math.abs(x-destX) + Math.abs(y-destY) > 1) {
            checkIsValidJump(destX, destY);        }
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

    }

	
    private boolean checkIsValidJump (int xdist, int ydist) throws InvalidMovementException {
        //a vertical jump
        int start = y, end = ydist;
        //a horizontal jump
        if (start == end) {
            start = x;
            end = xdist;
        }
        int[] Path = Board.getLineAsArray(start, end);
        //check if it is water in between start and end
        for (int i = 1; i < Path.length - 1; i++) {
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
                throw new InvalidMovementException("Cannot jump non river blocks");
            if (isBlocked)
                throw new InvalidMovementException("Cannot jump when there is animal in between");
        }
        return true;
    }
}