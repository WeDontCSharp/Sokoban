package skeleton.view.message;

import skeleton.model.Direction;

public class StepControlMessage extends ControlMessage {		//Lépésre utasítja az egyik munkást.
	public final int playerIndex;			//A lépésre utasított munkás indexe.
	public final Direction direction;		//Az irány, melybe a munkást lépésre utasítja.
	
	/**
	 * Lépésre utasítja az egyik munkást.
	 * 
	 * @param playerIndex	A lépésre utasított munkás indexe.
	 * @param direction		Az irány, melybe a munkást lépésre utasítja.
	 */
	public StepControlMessage(int playerIndex, Direction direction) {
		super(ControlMessageType.Step);
		this.playerIndex = playerIndex;
		this.direction = direction;
	}
	
}