/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package animalchess;

import animalchess.board.Board;
import animalchess.game.GameUI;


public class App {
	
    public static void main(String[] args) {
		Board board = Board.getInstance();
		GameUI gameUI = GameUI.getInstance();
    }

}
