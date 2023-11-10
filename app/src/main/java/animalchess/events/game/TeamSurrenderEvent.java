package animalchess.events.game;

import animalchess.utils.common.WrappedTeam;
import animalchess.utils.event.Event;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamSurrenderEvent implements Event {
    private WrappedTeam team;
}
