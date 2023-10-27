package animalchess.events;

import animalchess.utils.common.WrappedAnimal;
import animalchess.utils.event.Event;

public interface AnimalEvent extends Event {

    WrappedAnimal getAnimal();

}
