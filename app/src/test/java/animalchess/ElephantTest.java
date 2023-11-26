package animalchess;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class ElephantTest {
    @Test
    public void test_ElephantEat1() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal blue_mouse = board.getTarget(0,6);
        Animal red_Elephant = board.getTarget(0,2);
        blue_mouse.setPosition(0,5);
        red_Elephant.Move(0,3);
        blue_mouse.Move(0,4);
        Exception exception = assertThrows(Exception.class,()->red_Elephant.Move(0,4));
        assertEquals("you cannot eat mouse as an Elephant.",exception.getMessage());
    }

    @Test
    public void test_ElephantEat2() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal blue_cat = board.getTarget(5,7);
        Animal red_Elephant = board.getTarget(0,2);
        blue_cat.setPosition(0,5);
        red_Elephant.Move(0,3);
        blue_cat.Move(0,4);
        assertDoesNotThrow(()->red_Elephant.Move(0,4));
    }
}
