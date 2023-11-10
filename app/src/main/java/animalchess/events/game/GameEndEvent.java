package animalchess.events.game;

import animalchess.utils.common.WrappedGameResult;
import animalchess.utils.event.Event;
import lombok.Getter;

public class GameEndEvent implements Event {
    @Getter
    private WrappedGameResult result;
}
