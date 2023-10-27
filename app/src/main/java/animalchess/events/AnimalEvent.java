package animalchess.events;

import animalchess.animals.Animal;
import animalchess.utils.event.Event;

public interface AnimalEvent extends Event {

    Animal getAnimal();

}
