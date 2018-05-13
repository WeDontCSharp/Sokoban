package skeleton.view.message;

public class HealthStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;		//A pontot kapó vagy veszítõ játékos indexe.
	public final int newHP;				//A játékos új pontszáma.
	
	/**
	 * Egy dolgozót életeinek számát változtatja a kijelzõ felületén.
	 * 
	 * @param playerIndex	A pontot kapó vagy veszítõ játékos indexe.
	 * @param newHP			A játékos új pontszáma.
	 */
	public HealthStateChangeMessage(int playerIndex, int newHP) {
		super(StateChangeMessageType.Health);
		this.playerIndex = playerIndex;
		this.newHP = newHP;
	}
	
}