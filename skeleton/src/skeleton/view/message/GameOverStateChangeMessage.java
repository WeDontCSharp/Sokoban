package skeleton.view.message;

import skeleton.model.Warehouse;

public class GameOverStateChangeMessage extends StateChangeMessage {

	public final Warehouse.EndType endType;
	public final int playerIndex;
	
	/**
	 * A j�t�k v�g�t jelzi
	 * 
	 * @param end			Megadja, hogy mi�rt �rt v�get a j�t�k
	 * @param playerIdx		A gy�ztes j�t�kos index�t adja meg
	 */
	public GameOverStateChangeMessage(Warehouse.EndType end, int playerIdx) {
		super(StateChangeMessageType.GameOver);
		this.endType = end;
		this.playerIndex = playerIdx;
	}

}
