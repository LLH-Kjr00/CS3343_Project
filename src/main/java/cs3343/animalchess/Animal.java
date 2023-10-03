package cs3343.animalchess;

import cs3343.animalchess.util.InvalidMovementException;

public abstract class Animal {
    // R: red team, G: green team
    private String color;
    // coordinate
    private int x, y;
    // size of the animal
    protected int weight;
    private boolean trapped = false;
    protected Board board;

    public Animal (String color, int xCoordinate, int yCoordinate, int weight, Board board){
        this.color = color;
        this.x = xCoordinate;
        this.y = yCoordinate;
        this.weight = weight;
        this.board = board;

        board.addAnimal2Board(this, x, y);
    }
    //rules for general:
    //1. Cannot move more than one block
    //2. X and Y cannot be the same as original location
    //3. Cannot move outside of chess board
    //4. Cannot enter river
    //5. Cannot move into friendly animal or trap or Base
    //6. Cannot attempt to eat larger animal
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
        if (board.isOccupiedByFriendly(xdist, ydist, color)) {
            throw new InvalidMovementException("Cannot move into friendly units");
        }


    }
    public void Move (int x, int y){

    }

    public void Eat(Animal victim){

    }


}
