package skeleton.view.message;

import skeleton.model.Crate;
import skeleton.model.Field;

public class CrateStepStateChangeMessage extends StepStateChangeMessage {
    public final Crate crate;

    public CrateStepStateChangeMessage(Crate crate, Field fieldFrom, Field fieldTo){
        super(StateChangeMessageType.CrateStep, fieldFrom, fieldTo);
        this.crate = crate;
    }
}