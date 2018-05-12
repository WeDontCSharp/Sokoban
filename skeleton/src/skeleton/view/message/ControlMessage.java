package skeleton.view.message;

public abstract class ControlMessage {
	public final ControlMessageType type;

	public ControlMessage(ControlMessageType type) {
		this.type = type;
	}
}
