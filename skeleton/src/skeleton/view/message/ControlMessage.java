package skeleton.view.message;

public abstract class ControlMessage {
	public final ControlMessageType type;		//A kontroll �zenet konkr�t t�pusa
	
	/**
	 * Az ir�ny�t�s �zenetek �se, a munk�sokat utas�tja
	 * 
	 * @param type	Az �llapotv�ltoz�s �zenet konkr�t t�pusa
	 */
	public ControlMessage(ControlMessageType type) {
		this.type = type;
	}
}
