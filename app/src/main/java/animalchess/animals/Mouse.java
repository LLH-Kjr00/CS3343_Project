package animalchess.animals;

import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class Mouse extends Animal {

    public Mouse (String color, int xCoordinate, int yCoordinate, int weight, Board board){
        super(color, xCoordinate, yCoordinate, weight, board);
    }

    //new rule: Mouse can go in water
    @Override
    public void checkIsValidMove(int xdist, int ydist) throws InvalidMovementException {
        if ((Math.abs(x-xdist) + Math.abs(y-ydist)) > 1) {
            throw new InvalidMovementException("Cannot move more than one block");
        }
        if ((Math.abs(x-xdist) + Math.abs(y-ydist)) == 0) {
            throw new InvalidMovementException("Invalid movement! Cannot move into origin location!");
        }
        if (board.isOutBound(xdist, ydist)) {
            throw new InvalidMovementException("Cannot move outside of board");
        }
        if (board.isOccupiedByFriendly(xdist, ydist, color)) {
            throw new InvalidMovementException("Cannot move into friendly units");
        }
    }

    //additional rule: cannot eat animal from different medium
    @Override
    public void Move (int x, int y) throws InvalidMovementException{
        Animal target = board.getTarget(x, y);
        if (target != null && target.color != this.color)
            if (board.isInWater(x, y) != board.isInWater(target.x, target.y)) {
                Eat(target);
            } else {
                throw new InvalidMovementException("Cannot not eat animal from different terrain");
            }
        super.MoveTo(x ,y);
    }

    //additional rule: Mouse can eat elephant
    @Override
    public void Eat(Animal victim) throws InvalidMovementException{
        if (victim.weight==8)
            board.removeAnimal(victim.x, victim.y);
        else
            super.Eat(victim);
    }
}