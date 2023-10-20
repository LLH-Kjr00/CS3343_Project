package animalchess.command;

import animalchess.animals.Animal;
import animalchess.exceptions.InvalidMovementException;


public class Move_command implements GameCommand {
	private Animal actor;
	private int DestPosX;
	private int DestPosY;
	public Move_command (Animal actor,  int DestPosX, int DestPosY) {
		this.actor = actor;
		this.DestPosX = DestPosX ;
		this.DestPosY = DestPosY;
	}
	public void execute() {
		try {
			actor.Move(DestPosX,DestPosY);
		} catch (InvalidMovementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	public String log() {
		return(actor.get_Owner()+"'s"+actor.toString() + "has moved from ("+actor.get_xCoordinate()+","+actor.get_yCoordinate()+")"+" to ("+DestPosX+","+DestPosY+")");
	};
}
