package skeleton.view;

import skeleton.model.Field;

public class StepStateChangeMessage extends StateChangeMessage {
    public final Field fieldFrom;
    public final Field fieldTo;
	
	public StepStateChangeMessage(StateChangeMessageType type, Field fieldFrom, Field fieldTo) {
		super(type);
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
	}
	
}