package skeleton.view;

public abstract class ControlMessage {
	public final ControlMessageType type;

	public ControlMessage(ControlMessageType type) {
		this.type = type;
	}
}
