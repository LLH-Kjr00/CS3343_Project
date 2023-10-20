package animalchess.animals;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class Animal {
    // R: red team, G: green team
    public String color;
    // coordinate
    protected int x, y;
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
        if (board.isOccupiedByFriendly(xdist, ydist, color)) {
            throw new InvalidMovementException("Cannot move into friendly units");
        }

    }
    public void Move (int x, int y) throws InvalidMovementException{
        Animal target = board.getTarget(x, y);
        if (target != null && target.color != this.color)
            Eat(target);
        MoveTo(x ,y);
    }

    protected void MoveTo (int x, int y) {
        board.removeAnimal(this.x, this.y);
        board.addAnimal2Board(this, x, y);
        //Explain: you cannot move from one trap to another so moving from trap must mean out of trap
        if (trapped) {
            trapped = false;
        } else if(Board.isTrap(x, y, this.color)) {
            trapped = true;
        }
        this.x = x;
        this.y = y;
    }

    public void Eat(Animal victim) throws InvalidMovementException{
        if (victim.weight>this.weight && !victim.trapped)
            throw new InvalidMovementException("Cannot eat bigger target");
        else
            board.removeAnimal(victim.x, victim.y);
    }


}