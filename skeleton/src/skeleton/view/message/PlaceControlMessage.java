package skeleton.view.message;

public class PlaceControlMessage extends ControlMessage {		//A munk�sn�l lev� t�rgy letev�s�re utas�tja �t.
	public final int playerIndex;		// A letev�sre utas�tott munk�s indexe.
	
	/**
	 * A munk�sn�l lev� t�rgy letev�s�re utas�tja �t
	 * 
	 * @param playerIndex	A letev�sre utas�tott munk�s indexe.
	 */
	public PlaceControlMessage(int playerIndex) {
		super(ControlMessageType.Place);
		this.playerIndex = playerIndex;
	}
	
}