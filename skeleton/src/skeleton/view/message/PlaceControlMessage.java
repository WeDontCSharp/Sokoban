package skeleton.view.message;

public class PlaceControlMessage extends ControlMessage {		//A munkásnál levõ tárgy letevésére utasítja õt.
	public final int playerIndex;		// A letevésre utasított munkás indexe.
	
	/**
	 * A munkásnál levõ tárgy letevésére utasítja õt
	 * 
	 * @param playerIndex	A letevésre utasított munkás indexe.
	 */
	public PlaceControlMessage(int playerIndex) {
		super(ControlMessageType.Place);
		this.playerIndex = playerIndex;
	}
	
}