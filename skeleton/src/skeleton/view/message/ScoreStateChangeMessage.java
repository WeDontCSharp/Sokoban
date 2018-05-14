package skeleton.view.message;

public class ScoreStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;		//A pontot kapó vagy veszítõ játékos indexe.
	public final int newScore;			//A játékos új pontszáma.
	
	/**
	 * Egy dolgozót pontszámát változtatja a kijelzõ felületén.
	 * 
	 * @param playerIndex	A pontot kapó vagy veszítõ játékos indexe.
	 * @param newScore		A játékos új pontszáma.
	 */
	public ScoreStateChangeMessage(int playerIndex, int newScore) {
		super(StateChangeMessageType.Score);
		this.playerIndex = playerIndex;
		this.newScore = newScore;
	}
	
}
