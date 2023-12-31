/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package animalchess;

import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.exceptions.InvalidMovementException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    @Test
    public void test_ValidMove1() {

        Board board = Board.getInstance();
        board.init_board();
        Animal dog = board.getTarget(5,1);
        Exception exception = assertThrows(Exception.class,()->dog.Move(7,1));
        assertEquals("you cannot move more than one block.",exception.getMessage());
    }

    @Test
    public void test_ValidMove2() {

        Board board = Board.getInstance();
        board.init_board();
        Animal dog = board.getTarget(5,1);
        Exception exception = assertThrows(Exception.class,()->dog.Move(5,5),"you cannot move more than one block.");
        assertEquals("you cannot move more than one block.",exception.getMessage());
    }
    @Test
    public void test_ValidMove3() {

        Board board = Board.getInstance();
        board.init_board();
        Animal dog = board.getTarget(5,1);
        Exception exception = assertThrows(Exception.class,()->dog.Move(6,2),"you cannot move more than one block.");
        assertEquals("you cannot move more than one block.",exception.getMessage());
    }

    @Test
    public void test_ValidMove4() {

        Board board = Board.getInstance();
        board.init_board();
        Animal dog = board.getTarget(5,1);
        Exception exception = assertThrows(Exception.class,()->dog.Move(5,1),"you cannot move into origin location.");
        assertEquals("you cannot move into origin location.",exception.getMessage());
    }
    
    @Test
    public void test_ValidMove5() {
        Board board = Board.getInstance();
        board.init_board();
        Animal leopard = board.getTarget(4,2);
        Exception exception = assertThrows(Exception.class,()->leopard.Move(4,3),"this animal cannot go into water.");
        assertEquals("this animal cannot go into water.",exception.getMessage());
    }

    @Test
    public void test_ValidMove6() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal leopard = board.getTarget(4,2);
        Animal wolf = board.getTarget(2,2);
        wolf.Move(3,2);
        Exception exception = assertThrows(Exception.class,()->leopard.Move(3,2),"you cannot move into friendly units.");
        assertEquals("you cannot move into friendly units.",exception.getMessage());
    }

    @Test
    public void test_ValidMove7(){
        Board board = Board.getInstance();
        board.init_board();
        Animal leopard = board.getTarget(4,2);
        leopard.setPosition(4,0);
        Exception exception = assertThrows(Exception.class,()->leopard.Move(3,0),"you cannot enter friendly den.");
        assertEquals("you cannot enter friendly den.",exception.getMessage());
    }

    @Test
    public void test_ValidMove8(){
        Board board = Board.getInstance();
        board.init_board();
        Animal leopard = board.getTarget(4,2);
        assertDoesNotThrow(()->leopard.Move(4,1));
    }

    @Test
    public void test_Eat1() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal red_leopard = board.getTarget(4,2);
        Animal blue_Elephant = board.getTarget(6,6);

        red_leopard.setPosition(6,7);
        Exception exception =assertThrows(Exception.class,()->red_leopard.Move(6,6),"you cannot eat animal with more strength than yours.");
        assertEquals("you cannot eat animal with more strength than yours.",exception.getMessage());
    }

    @Test
    public void test_Eat2() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal red_leopard = board.getTarget(4,2);
        Animal blue_Elephant = board.getTarget(6,6);
        blue_Elephant.setPosition(3,1);
        red_leopard.setPosition(2,1);
        assertDoesNotThrow(()->red_leopard.Move(3,1));
    }

    @Test
    public void test_Eat3() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal red_leopard = board.getTarget(4,2);
//        Animal blue_Cat = board.getTarget(5,7);

        red_leopard.setPosition(5,6);
        assertDoesNotThrow(()->red_leopard.Move(5,7));
    }

    @Test
    public void test_Eat4() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal red_leopard = board.getTarget(4,2);
        Animal blue_leopard = board.getTarget(2,6);

        red_leopard.setPosition(3,5); //the red animal must move first
        red_leopard.Move(3,6);
        assertDoesNotThrow(()->blue_leopard.Move(3,6));
    }
}
