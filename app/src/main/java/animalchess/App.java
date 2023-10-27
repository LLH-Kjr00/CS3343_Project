/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package animalchess;

import animalchess.board.Board;
import animalchess.game.GameUI;
import animalchess.utils.EventManager;

public class App {

    private static EventManager eventManager;
    private static GameUI ui;
    private static Board board;

    public static void main(String[] args) {
        eventManager = new EventManager();
        ui = GameUI.getInstance();
    }

    public static EventManager getEventManager() {
        return eventManager;
    }

}
