package skeleton.view.message;

public class HealthStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;		//A pontot kap� vagy vesz�t� j�t�kos indexe.
	public final int newHP;				//A j�t�kos �j pontsz�ma.
	
	/**
	 * Egy dolgoz�t �leteinek sz�m�t v�ltoztatja a kijelz� fel�let�n.
	 * 
	 * @param playerIndex	A pontot kap� vagy vesz�t� j�t�kos indexe.
	 * @param newHP			A j�t�kos �j pontsz�ma.
	 */
	public HealthStateChangeMessage(int playerIndex, int newHP) {
		super(StateChangeMessageType.Health);
		this.playerIndex = playerIndex;
		this.newHP = newHP;
	}
	
}