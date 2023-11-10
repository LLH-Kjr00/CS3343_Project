package animalchess.game;

import animalchess.events.animal.*;
import animalchess.events.game.*;
import animalchess.events.tile.*;
import animalchess.utils.event.EventHandler;
import animalchess.utils.event.EventListener;
import animalchess.utils.event.EventPriority;
import animalchess.utils.provider.Inject;

@EventHandler
public class UIListener {

	@Inject
	private GameUI gameUI;

	@EventListener(priority = EventPriority.HIGH)
	public void onAnimalDeath(AnimalDeathEvent event) {
		System.out.println("Animal " + event.getAnimal().type() + " died at " + event.getAnimal().tile().location());
	}

	@EventListener(priority = EventPriority.HIGH)
	public void onAnimalAttack(AnimalAttackEvent event) {
		System.out.println("Animal " + event.getAttacker().type() + " attacked " + event.getAnimal().type() + " at "
				+ event.getAnimal().tile().location());
	}

	@EventListener(priority = EventPriority.HIGH)
	public void onAnimalMove(AnimalMoveEvent event) {
		System.out.println(
				"Animal " + event.getAnimal().type() + " moved from " + event.getFrom() + " to " + event.getTo());
	}
	
	@EventListener(priority = EventPriority.HIGHEST)
	public void onTileSelect(TileSelectEvent event) {
		System.out.println("Tile at " + event.getTile().location() + " was selected");
	}
	
	@EventListener
	public void onGameEnd(GameEndEvent event) {
		System.out.println("Game ended where Player 1 (" + event.getResult().getResults().get(0).getTeam() + ") " + event.getResult().getResults().get(0).getResultType());
		System.out.println("Game ended where Player 2 (" + event.getResult().getResults().get(1).getTeam() + ") " + event.getResult().getResults().get(0).getResultType());
		gameUI.determineWinner(event.getResult());
		gameUI.announce_Win();
	}
}
