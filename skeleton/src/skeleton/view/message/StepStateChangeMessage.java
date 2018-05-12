package skeleton.view.message;

import skeleton.model.Field;

public abstract class StepStateChangeMessage extends StateChangeMessage {
    public final Field fieldFrom;
    public final Field fieldTo;
    public float percent;
	
	public StepStateChangeMessage(StateChangeMessageType type, Field fieldFrom, Field fieldTo, float percent) {
		super(type);
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.percent = percent;
	}
	
}