package skeleton.view.message;

import skeleton.model.Field;

public abstract class FallStateChangeMessage extends StateChangeMessage {

	public final Field from;
	public final Field to;
	public float percent;
	
	public FallStateChangeMessage(StateChangeMessageType type, Field from, Field to) {
		super(type);
		this.from = from;
		this.to =  to;
		this.percent = 0.0f;
	}
	
}
