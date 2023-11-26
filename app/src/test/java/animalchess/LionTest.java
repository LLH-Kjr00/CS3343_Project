package animalchess;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class LionTest {
    //Lion
    @Test
    public void test_LionValidMove1() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(1,2);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(2,6));
        assertEquals("you cannot jump diagonally",exception.getMessage());
    }
    @Test
    public void test_LionValidMove2() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(3,2);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(3,6));
        assertEquals("you cannot jump over normal tiles.",exception.getMessage());
    }
    @Test
    public void test_LionValidMove3() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(3,2);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(6,2));
        assertEquals("you cannot jump over normal tiles.",exception.getMessage());
    }
    @Test
    public void test_LionValidMove4() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(1,6);
//        Exception exception = assertThrows(Exception.class,()->Lion.Move(2,2));
        assertDoesNotThrow(()->Lion.Move(1,2));
    }
    @Test
    public void test_LionValidMove5() throws InvalidMovementException {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(1,2);
        Animal blue_mouse = board.getTarget(0,6);
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(2,4);
        mouse.Move(1,4);
        blue_mouse.Move(0,5);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(1,6));
        assertEquals("you cannot jump when there is animal in between.",exception.getMessage());
    }

    @Test
    public void test_LionValidMove6() throws InvalidMovementException {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(0,3);
        Animal blue_mouse = board.getTarget(0,6);
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(1,2);
        mouse.Move(1,3);
        blue_mouse.Move(0,5);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(3,3));
        assertEquals("you cannot jump when there is animal in between.",exception.getMessage());
    }
    @Test
    public void test_LionValidMove7() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(6,0),"you cannot move into origin location.");
        assertEquals("you cannot move into origin location.",exception.getMessage());
    }
    @Test
    public void test_LionValidMove8() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(0,1);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(-1,1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_LionValidMove9() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(6,-1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }
    @Test
    public void test_LionValidMove10() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(7,0),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_LionValidMove11() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(6,8);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(6,9),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_LionValidMove12() {

        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(5,2);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(5,3),"this animal cannot go into water.");
        assertEquals("this animal cannot go into water.",exception.getMessage());
    }
    @Test
    public void test_LionValidMove13() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(4,2);
        Animal wolf = board.getTarget(2,2);
        wolf.Move(3,2);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(3,2),"you cannot move into friendly units.");
        assertEquals("you cannot move into friendly units.",exception.getMessage());
    }

    @Test
    public void test_LionValidMove14(){
        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        Lion.setPosition(4,0);
        Exception exception = assertThrows(Exception.class,()->Lion.Move(3,0),"you cannot enter friendly den.");
        assertEquals("you cannot enter friendly den.",exception.getMessage());
    }

    @Test
    public void test_LionValidMove15(){
        Board board = Board.getInstance();
        board.init_board();
        Animal Lion = board.getTarget(6,0);
        assertDoesNotThrow(()->Lion.Move(6,1));
    }
}
