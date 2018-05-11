package skeleton.view;

public class HealthStateChangeMessage extends StateChangeMessage {
	public final int playerIndex;
	public final int newHP;
	
	public HealthStateChangeMessage(int playerIndex, int newHP) {
		super(StateChangeMessageType.Health);
		this.playerIndex = playerIndex;
		this.newHP = newHP;
	}
	
}