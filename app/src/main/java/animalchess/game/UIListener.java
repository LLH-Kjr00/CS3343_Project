package animalchess.game;

import animalchess.events.animal.AnimalDeathEvent;
import animalchess.utils.event.EventHandler;
import animalchess.utils.event.EventListener;
import animalchess.utils.event.EventPriority;

@EventHandler
public class UIListener {

    @EventListener(priority = EventPriority.NORMAL)
    public void onAnimalDeath(AnimalDeathEvent event) {
        System.out.println("Animal " + event.getAnimal().type() + " died at " + event.getAnimal().tile().location());
    }

}
