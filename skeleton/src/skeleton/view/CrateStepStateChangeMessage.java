package skeleton.view;

import skeleton.model.Crate;
import skeleton.model.Field;

public class CrateStepStateChangeMessage extends StepStateChangeMessage {
    public final Crate crate;

    public CrateStepStateChangeMessage(Crate crate, Field fieldFrom, Field fieldTo, int process){
        super(StateChangeMessageType.CrateStep, fieldFrom, fieldTo, process);
        this.crate = crate;
    }
}