package animalchess.events.animal;

import animalchess.utils.common.WrappedAnimal;
import animalchess.utils.common.WrappedLocation;
import animalchess.utils.event.Cancellable;
import lombok.Getter;

@Getter
public class AnimalMoveEvent implements AnimalEvent, Cancellable {

    private WrappedAnimal animal;
    private WrappedLocation from;
    private WrappedLocation to;

    private boolean cancelled = false;

    @Override
    public void cancel() {
        cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

	@Override
	public Animal getAnimal() {
		// TODO Auto-generated method stub
		return null;
	}

}
