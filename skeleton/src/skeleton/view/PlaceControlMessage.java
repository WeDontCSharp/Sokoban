package skeleton.view;

public class PlaceControlMessage extends ControlMessage {
	public final int playerIndex;
	
	public PlaceControlMessage(int playerIndex) {
		super(ControlMessageType.Place);
		this.playerIndex = playerIndex;
	}
	
}