package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerFallStateChangeMessage extends FallStateChangeMessage {
    public final int playerIndex;
    public final Field playerSpawn;
    public final Direction direction;
	
	public WorkerFallStateChangeMessage(int playerIndex, Field playerSpawn, Direction dir, Field from, Field to) {
		super(StateChangeMessageType.WorkerFall, from, to);
        this.playerIndex = playerIndex;
        this.playerSpawn = playerSpawn;
        this.direction = dir;
	}
	
}