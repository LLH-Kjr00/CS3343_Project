package animalchess.game;

import animalchess.animals.Animal;
import animalchess.board.Board;
import animalchess.board.Tiles.Tile;
import animalchess.events.LocationSelectEvent;
import animalchess.events.animal.AnimalMoveEvent;
import animalchess.events.tile.TileSelectEvent;
import animalchess.utils.common.WrappedLocation;
import animalchess.utils.event.EventHandler;
import animalchess.utils.event.EventListener;
import animalchess.utils.event.EventPriority;
import animalchess.utils.provider.Inject;

import static animalchess.game.BoardPanel.horizontalAxis;
import static animalchess.game.BoardPanel.verticalAxis;

/**
 * Listener for backend side
 */
@EventHandler
public class GameListener {

    @Inject
    private GameUI gameUI;
    @Inject
    private Board board;

    @EventListener
    public void onMove(AnimalMoveEvent event) {
        // Implement move event here
    }

    // Code from the original call_TileSelect function
    @EventListener(priority = EventPriority.HIGHEST)
    public void onTileSelect(LocationSelectEvent event) {
        WrappedLocation loc = event.getLocation();
        Tile tile = board.getTile(loc);
        Animal target = tile.getAnimal();

        GameUI.logArea.append("Clicking the tile at (" + verticalAxis[loc.y()] + "," + horizontalAxis[loc.x()] + ")\n");
        if (target == null) {
            GameUI.logArea.append("It is a " + tile + " with no animal.\n");
        } else {
            GameUI.logArea.append("It is a " + tile + " with a/an " + target + " that "
                    + target.get_Owner() + " can choose.\n");

            GameUI.logArea.append("\n");
            if (target.get_isRed() == GameUI.is_P1_Turn) {
                GameUI.logArea.append("You can choose the animal in this tile to move.\n");
            } else {
                GameUI.logArea.append("You cannot choose the animal in this tile to move.\n");
                event.cancel();
            }
        }

        GameUI.logArea.append("\n");
    }

}
