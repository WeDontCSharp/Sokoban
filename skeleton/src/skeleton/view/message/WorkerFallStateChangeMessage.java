package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerFallStateChangeMessage extends FallStateChangeMessage {
    public final int playerIndex;
    public final Direction direction;
	
	public WorkerFallStateChangeMessage(int playerIndex, Direction dir, Field from, Field to) {
		super(StateChangeMessageType.WorkerFall, from, to);
        this.playerIndex = playerIndex;
        this.direction = dir;
	}
	
}