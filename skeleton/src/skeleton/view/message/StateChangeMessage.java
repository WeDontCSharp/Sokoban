package skeleton.view.message;

public abstract class StateChangeMessage {		
	public final StateChangeMessageType type;		//Az állapotváltozás üzenet konkrét típusa
	
	/**
	 * Az irányítás üzenetek õse.
	 * 
	 * @param type	Az állapotváltozás üzenet konkrét típusa
	 */
	public StateChangeMessage(StateChangeMessageType type) {
		this.type = type;
	}
}
