package animalchess.events;

import animalchess.animals.Animal;
import animalchess.utils.common.Location;
import animalchess.utils.common.Team;
import animalchess.utils.event.Cancellable;
import lombok.Data;

@Data
public class AnimalMoveEvent implements AnimalEvent, Cancellable {

    private Animal animal;
    private Location from;
    private Location to;
    private Team team;

    private boolean cancelled = false;

    @Override
    public void cancel() {
        cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

}
