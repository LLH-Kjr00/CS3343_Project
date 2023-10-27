package animalchess.command;

import animalchess.animals.Animal;
import animalchess.exceptions.InvalidMovementException;


public class Eat_command implements GameCommand {
	private Animal actor;
	private Animal victim;
	public Eat_command (Animal actor, Animal victim) {
		this.actor = actor;
		this.victim = victim;
	}
	public void execute() {
		try {
			actor.Eat(victim);
		} catch (InvalidMovementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	public String log() {
		return(actor.get_Owner()+"'s"+actor.toString() + "has eaten "+ victim.get_Owner()+"'s"+victim.toString());
	};
}
