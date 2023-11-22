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
        Animal dog = board.getTarget(5,1);
        dog.setPosition(0,1);
        Exception exception = assertThrows(Exception.class,()->dog.Move(-1,1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_ValidMove6() {

        Board board = Board.getInstance();
        board.init_board();
        Animal dog = board.getTarget(5,1);
        dog.setPosition(5,0);
        Exception exception = assertThrows(Exception.class,()->dog.Move(5,-1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }
    @Test
    public void test_ValidMove7() {

        Board board = Board.getInstance();
        board.init_board();
        Animal dog = board.getTarget(5,1);
        dog.setPosition(6,1);
        Exception exception = assertThrows(Exception.class,()->dog.Move(7,1),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }

    @Test
    public void test_ValidMove8() {

        Board board = Board.getInstance();
        board.init_board();
        Animal dog = board.getTarget(5,1);
        dog.setPosition(5,8);
        Exception exception = assertThrows(Exception.class,()->dog.Move(5,9),"you cannot move outside of board.");
        assertEquals("you cannot move outside of board.",exception.getMessage());
    }
    @Test
    public void test_ValidMove9() {
        Board board = Board.getInstance();
        board.init_board();
        Animal leopard = board.getTarget(4,2);
        Exception exception = assertThrows(Exception.class,()->leopard.Move(4,3),"this animal cannot go into water.");
        assertEquals("this animal cannot go into water.",exception.getMessage());
    }

    @Test
    public void test_ValidMove10() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal leopard = board.getTarget(4,2);
        Animal wolf = board.getTarget(2,2);
        wolf.Move(3,2);
        Exception exception = assertThrows(Exception.class,()->leopard.Move(3,2),"you cannot move into friendly units.");
        assertEquals("you cannot move into friendly units.",exception.getMessage());
    }

    @Test
    public void test_ValidMove11(){
        Board board = Board.getInstance();
        board.init_board();
        Animal leopard = board.getTarget(4,2);
        leopard.setPosition(4,0);
        Exception exception = assertThrows(Exception.class,()->leopard.Move(3,0),"you cannot enter friendly den.");
        assertEquals("you cannot enter friendly den.",exception.getMessage());
    }

    @Test
    public void test_ValidMove12(){
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
//        Animal blue_Cat = board.getTarget(5,7);

        red_leopard.setPosition(5,6);
        assertDoesNotThrow(()->red_leopard.Move(5,7));
    }

    @Test
    public void test_Eat3() throws InvalidMovementException {
        Board board = Board.getInstance();
        board.init_board();
        Animal red_leopard = board.getTarget(4,2);
        Animal blue_leopard = board.getTarget(2,6);

        red_leopard.setPosition(3,5); //the red animal must move first
        red_leopard.Move(3,6);
        assertDoesNotThrow(()->blue_leopard.Move(3,6));
    }

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
//        Animal red_Cat = board.getTarget(1,1);
        blue_mouse.setPosition(2,1);
        Exception exception = assertThrows(Exception.class,()->blue_mouse.Move(1,1));
        assertEquals("you cannot eat animal with more strength than yours.",exception.getMessage());
    }



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
