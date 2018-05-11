package skeleton.view;

import skeleton.model.Spawn;

public class WorkerFallStateChangeMessage extends StateChangeMessage {
    public final int playerIndex;
    public final Spawn playerSpawn;
	
	public WorkerFallStateChangeMessage(int playerIndex, Spawn playerSpawn) {
		super(StateChangeMessageType.WorkerFall);
        this.playerIndex = playerIndex;
        this.playerSpawn = playerSpawn;
	}
	
}