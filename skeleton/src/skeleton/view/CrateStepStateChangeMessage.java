package skeleton.view;

import skeleton.model.Crate;

public class CrateStepStateChangeMessage extends StepStateChangeMessage {
    public final Crate crate;

    public CrateStepStateChangeMessage(Crate crate){
        super(StateChangeMessageType.CrateStep);
        this.crate = crate;
    }
}