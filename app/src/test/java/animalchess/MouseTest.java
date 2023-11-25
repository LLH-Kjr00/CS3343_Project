package animalchess;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class MouseTest {
	//Mouse
    @Test
    public void test_MouseValidMove1() {

        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(4,2));
        assertEquals("you cannot move more than one block.",exception.getMessage());
    }

    @Test
    public void test_MouseValidMove2() {

        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(6,4),"you cannot move more than one block.");
        assertEquals("you cannot move more than one block.",exception.getMessage());
    }
    @Test
    public void testMouseValidMove3() {

        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(7,3),"you cannot move more than one block.");
        assertEquals("you cannot move more than one block.",exception.getMessage());
    }

    @Test
    public void test_MouseValidMove4() {

        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(6,2),"you cannot move into origin location.");
        assertEquals("you cannot move into origin location.",exception.getMessage());
    }
    @Test
    public void test_MouseValidMove5() {

        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(0,1);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(-1,1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_MouseValidMove6() {

        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(5,0);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(5,-1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }
    @Test
    public void test_MouseValidMove7() {

        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(6,1);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(7,1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_MouseValidMove8() {

        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(5,8);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(5,9),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_MouseValidMove9() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(4,2);
        Animal wolf = board.getTarget(2,2);
        wolf.Move(3,2);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(3,2),"you cannot move into friendly units.");
        assertEquals("you cannot move into friendly units.",exception.getMessage());
    }

    @Test
    public void test_MouseValidMove10(){
        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(4,0);
        Exception exception = assertThrows(Exception.class,()->mouse.Move(3,0),"you cannot enter friendly den.");
        assertEquals("you cannot enter friendly den.",exception.getMessage());
    }

    @Test
    public void test_MouseValidMove11(){
        Board board = Board.getInstance();
        board.init_board();
        Animal mouse = board.getTarget(6,2);
        assertDoesNotThrow(()->mouse.Move(6,1));
    }

    @Test
    public void test_MouseEat1() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal red_mouse = board.getTarget(6,2);
        Animal blue_Elephant = board.getTarget(6,6);
        red_mouse.setPosition(6,5);
        assertDoesNotThrow(()->red_mouse.Move(6,6));
    }

    @Test
    public void test_MouseEat2() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal red_mouse = board.getTarget(6,2);
        Animal blue_Elephant = board.getTarget(6,6);
        red_mouse.setPosition(4,5);
        blue_Elephant.setPosition(3,3);
        red_mouse.Move(4,4);
        blue_Elephant.Move(3,4);
        Exception exception = assertThrows(Exception.class,()->red_mouse.Move(3,4));
        assertEquals("you cannot not kill animal from different terrain.",exception.getMessage());
    }

    @Test
    public void test_MouseEat3() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal blue_mouse = board.getTarget(0,6);
        Animal red_Elephant = board.getTarget(0,2);
        blue_mouse.setPosition(0,4);
        red_Elephant.Move(0,3);
        assertDoesNotThrow(()->blue_mouse.Move(0,3));
    }

    @Test
    public void test_MouseEat4() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal blue_mouse = board.getTarget(0,6);
        Animal red_Cat = board.getTarget(1,1);
        red_Cat.setPosition(3,3);
        blue_mouse.setPosition(3,5);
        red_Cat.Move(3,4);
        Exception exception = assertThrows(Exception.class,()->blue_mouse.Move(3,4));
        assertEquals("you cannot eat animal with more strength than yours.",exception.getMessage());
    }


}
