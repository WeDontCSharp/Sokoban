package skeleton.view;

import skeleton.model.Field;

public class StepStateChangeMessage extends StateChangeMessage {
    public final Field fieldFrom;
    public final Field fieldTo;
    public final int process;
	
	public StepStateChangeMessage(Field fieldFrom, Field fieldTo, int process) {
		super(StateChangeMessageType.Step);
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.process = process;
	}
	
}