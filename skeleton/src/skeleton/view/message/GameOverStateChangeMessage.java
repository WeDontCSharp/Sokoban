package skeleton.view.message;

import skeleton.model.Warehouse;

public class GameOverStateChangeMessage extends StateChangeMessage {

	public final Warehouse.EndType endType;
	public final int playerIndex;
	
	public GameOverStateChangeMessage(Warehouse.EndType end, int playerIdx) {
		super(StateChangeMessageType.GameOver);
		this.endType = end;
		this.playerIndex = playerIdx;
	}

}
