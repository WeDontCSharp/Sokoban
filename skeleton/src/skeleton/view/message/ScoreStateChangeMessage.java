package skeleton.view.message;

public class ScoreStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;		//A pontot kap� vagy vesz�t� j�t�kos indexe.
	public final int newScore;			//A j�t�kos �j pontsz�ma.
	
	/**
	 * Egy dolgoz�t pontsz�m�t v�ltoztatja a kijelz� fel�let�n.
	 * 
	 * @param playerIndex	A pontot kap� vagy vesz�t� j�t�kos indexe.
	 * @param newScore		A j�t�kos �j pontsz�ma.
	 */
	public ScoreStateChangeMessage(int playerIndex, int newScore) {
		super(StateChangeMessageType.Score);
		this.playerIndex = playerIndex;
		this.newScore = newScore;
	}
	
}
