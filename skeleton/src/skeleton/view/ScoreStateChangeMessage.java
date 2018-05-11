package skeleton.view;

public class ScoreStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;
	public final int newScore;
	
	public ScoreStateChangeMessage(int playerIndex, int newScore) {
		super(StateChangeMessageType.Score);
		this.playerIndex = playerIndex;
		this.newScore = newScore;
	}
	
}
