package animalchess;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;

public class TigerTest {
	//Tiger
    @Test
    public void test_TigerValidMove1() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(1,2);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(2,6));
        assertEquals("you cannot jump diagonally",exception.getMessage());
    }
    @Test
    public void test_TigerValidMove2() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(3,2);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(3,6));
        assertEquals("you cannot jump over normal tiles.",exception.getMessage());
    }

    @Test
    public void test_TigerValidMove3() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(3,2);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(5,2));
        assertEquals("you cannot jump over normal tiles.",exception.getMessage());
    }
    @Test
    public void test_TigerValidMove4() throws InvalidMovementException {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(1,2);
        Animal blue_mouse = board.getTarget(0,6);
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(1,3);
        mouse.Move(1,4);
        blue_mouse.Move(0,5);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(1,6));
        assertEquals("you cannot jump when there is animal in between.",exception.getMessage());
    }

    @Test
    public void test_TigerValidMove5() throws InvalidMovementException {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(0,3);
        Animal blue_mouse = board.getTarget(0,6);
        Animal mouse = board.getTarget(6,2);
        mouse.setPosition(1,2);
        mouse.Move(1,3);
        blue_mouse.Move(0,5);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(3,3));
        assertEquals("you cannot jump when there is animal in between.",exception.getMessage());
    }
    @Test
    public void test_TigerValidMove6() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(1,2);
        assertDoesNotThrow(()->tiger.Move(1,6));
    }
    @Test
    public void test_TigerValidMove7() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(0,0),"you cannot move into origin location.");
        assertEquals("you cannot move into origin location.",exception.getMessage());
    }
    @Test
    public void test_TigerValidMove8() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(0,1);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(-1,1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_TigerValidMove9() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(0,-1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }
    @Test
    public void test_TigerValidMove10() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(6,0);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(7,0),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_TigerValidMove11() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(0,8);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(0,9),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_TigerValidMove12() {

        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(1,2);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(1,3),"this animal cannot go into water.");
        assertEquals("this animal cannot go into water.",exception.getMessage());
    }
    @Test
    public void test_TigerValidMove13() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(4,2);
        Animal wolf = board.getTarget(2,2);
        wolf.Move(3,2);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(3,2),"you cannot move into friendly units.");
        assertEquals("you cannot move into friendly units.",exception.getMessage());
    }

    @Test
    public void test_TigerValidMove14(){
        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        tiger.setPosition(4,0);
        Exception exception = assertThrows(Exception.class,()->tiger.Move(3,0),"you cannot enter friendly den.");
        assertEquals("you cannot enter friendly den.",exception.getMessage());
    }

    @Test
    public void test_TigerValidMove15(){
        Board board = Board.getInstance();
        board.init_board();
        Animal tiger = board.getTarget(0,0);
        assertDoesNotThrow(()->tiger.Move(0,1));
    }

}
