package skeleton.view.message;

public abstract class StateChangeMessage {		
	public final StateChangeMessageType type;		//Az �llapotv�ltoz�s �zenet konkr�t t�pusa
	
	/**
	 * Az ir�ny�t�s �zenetek �se.
	 * 
	 * @param type	Az �llapotv�ltoz�s �zenet konkr�t t�pusa
	 */
	public StateChangeMessage(StateChangeMessageType type) {
		this.type = type;
	}
}
