package animalchess.command;

import animalchess.animals.Animal;

public interface GameCommand {
	public void execute();
	public String log();

}
