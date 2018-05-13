package skeleton.view.message;

public abstract class ControlMessage {
	public final ControlMessageType type		//A kontroll üzenet konkrét típusa
	
	/**
	 * Az irányítás üzenetek õse, a munkásokat utasítja
	 * 
	 * @param type	Az állapotváltozás üzenet konkrét típusa
	 */
	public ControlMessage(ControlMessageType type) {
		this.type = type;
	}
}
