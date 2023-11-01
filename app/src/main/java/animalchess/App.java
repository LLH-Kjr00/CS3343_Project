/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package animalchess;

import animalchess.board.Board;
import animalchess.game.GameUI;
import animalchess.game.UIListener;
import animalchess.utils.event.EventListener;
import animalchess.utils.event.EventManager;
import animalchess.utils.provider.ProviderModule;

public class App {

    public static void main(String[] args) {

        EventManager eventManager = new EventManager();

        ProviderModule.builder()
                .singleton(eventManager)
                .annotationProcessor(EventListener.class, eventManager::registerListeners)
                .singleton(new Board())
                .singleton(GameUI.getInstance())
                .singleton(new UIListener())
                .resolve();
    }

}
