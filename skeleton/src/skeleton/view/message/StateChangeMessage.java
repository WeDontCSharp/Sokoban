package skeleton.view.message;

public abstract class StateChangeMessage {
	public final StateChangeMessageType type;

	public StateChangeMessage(StateChangeMessageType type) {
		this.type = type;
	}
}
