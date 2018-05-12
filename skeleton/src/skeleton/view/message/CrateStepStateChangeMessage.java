package skeleton.view.message;

import skeleton.model.Crate;
import skeleton.model.Field;

public class CrateStepStateChangeMessage extends StepStateChangeMessage {
    public final Crate crate;

    public CrateStepStateChangeMessage(Crate crate, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.CrateStep, fieldFrom, fieldTo, perc);
        this.crate = crate;
    }
}