package animalchess.events.tile;

import animalchess.utils.common.WrappedTile;
import animalchess.utils.event.Cancellable;

public class TileSelectEvent implements TileEvent, Cancellable {

    private WrappedTile tile;
    private boolean cancelled = false;

    @Override
    public WrappedTile getTile() {
        return tile;
    }

    @Override
    public void cancel() {
        cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

}
