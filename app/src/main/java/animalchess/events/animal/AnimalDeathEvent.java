package animalchess.events.animal;

import animalchess.utils.common.WrappedAnimal;
import animalchess.utils.event.Cancellable;
import lombok.Getter;

public class AnimalDeathEvent implements AnimalEvent, Cancellable {

    @Getter
    private WrappedAnimal animal;
    @Getter
    private WrappedAnimal attacker;
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
