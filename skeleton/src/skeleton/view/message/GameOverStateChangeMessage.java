package skeleton.view.message;

import skeleton.model.Warehouse;

public class GameOverStateChangeMessage extends StateChangeMessage {

	public final Warehouse.EndType endType;
	public final int playerIndex;
	
	/**
	 * A játék végét jelzi
	 * 
	 * @param end			Megadja, hogy miért ért véget a játék
	 * @param playerIdx		A gyõztes játékos indexét adja meg
	 */
	public GameOverStateChangeMessage(Warehouse.EndType end, int playerIdx) {
		super(StateChangeMessageType.GameOver);
		this.endType = end;
		this.playerIndex = playerIdx;
	}

}
