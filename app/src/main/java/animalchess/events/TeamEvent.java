package animalchess.events;

import animalchess.utils.common.WrappedTeam;
import animalchess.utils.event.Event;

public interface TeamEvent extends Event {
    WrappedTeam getTeam();
}
