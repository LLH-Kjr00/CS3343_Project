package animalchess.events;

import animalchess.utils.common.WrappedTile;
import animalchess.utils.event.Event;

public interface TileEvent extends Event {

    WrappedTile getTile();

}
