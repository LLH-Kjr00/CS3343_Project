package animalchess.events;

import animalchess.animals.Animal;
import animalchess.utils.Event;

public interface AnimalEvent extends Event {

    Animal getAnimal();

}
