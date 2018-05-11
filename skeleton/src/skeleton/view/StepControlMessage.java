package skeleton.view;

import skeleton.model.Direction;

public class StepControlMessage extends ControlMessage {
	public final int playerIndex;
	public final Direction direction;
	
	public StepControlMessage(int playerIndex, Direction direction) {
		super(ControlMessageType.Step);
		this.playerIndex = playerIndex;
		this.direction = direction;
	}
	
}