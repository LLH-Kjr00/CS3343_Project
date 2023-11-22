/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package animalchess;

import animalchess.animals.*;
import animalchess.board.Board;
import animalchess.board.Tiles.Den;
import animalchess.board.Tiles.Tile;
import animalchess.board.Tiles.TrapTile;
import animalchess.board.Tiles.WaterTile;
import animalchess.exceptions.InvalidMovementException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void check_Win1() throws InvalidMovementException {
        Board board = Board.getInstance();
//        board.init_board_test();
        board.init_board();
        Animal red_mouse = board.getTarget(6,2);
        Animal blue_mouse = board.getTarget(0,6);
        red_mouse.Move(6,3);
        blue_mouse.Move(0,5);

        red_mouse.Move(6,4);
        blue_mouse.Move(0,6);

        red_mouse.Move(6,5);
        blue_mouse.Move(0,5);

        red_mouse.Move(5,5);
        blue_mouse.Move(0,6);

        red_mouse.Move(4,5);
        blue_mouse.Move(0,5);

        red_mouse.Move(3,5);
        blue_mouse.Move(0,6);

        red_mouse.Move(3,6);
        blue_mouse.Move(0,5);

        red_mouse.Move(3,7);
        blue_mouse.Move(0,6);

        red_mouse.Move(3,8);
//        blue_mouse.Move(0,6);
        assertTrue(Board.is_P1_Win);

    }

    @Test
    public void check_Win2() throws InvalidMovementException {
        Board board = Board.getInstance();
//        board.init_board_test();
        board.init_board();
        Animal red_mouse = board.getTarget(6,2);
        Animal blue_mouse = board.getTarget(0,6);
        red_mouse.Move(6,1);
        blue_mouse.Move(1,6);

        red_mouse.Move(6,2);
        blue_mouse.Move(1,5);

        red_mouse.Move(6,1);
        blue_mouse.Move(2,5);

        red_mouse.Move(6,2);
        blue_mouse.Move(3,5);

        red_mouse.Move(6,1);
        blue_mouse.Move(3,4);

        red_mouse.Move(6,2);
        blue_mouse.Move(3,3);

        red_mouse.Move(6,1);
        blue_mouse.Move(3,2);

        red_mouse.Move(6,2);
        blue_mouse.Move(3,1);

        red_mouse.Move(6,1);
        blue_mouse.Move(3,0);
//        blue_mouse.Move(0,6);
        assertTrue(Board.is_P2_Win);

    }
}
