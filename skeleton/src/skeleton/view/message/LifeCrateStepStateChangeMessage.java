package skeleton.view.message;

import skeleton.model.Field;
import skeleton.model.LifeCrate;

public class LifeCrateStepStateChangeMessage extends StepStateChangeMessage {
    public final LifeCrate lifeCrate;

    public LifeCrateStepStateChangeMessage(LifeCrate lifeCrate, Field fieldFrom, Field fieldTo){
        super(StateChangeMessageType.LifeCrateStep, fieldFrom, fieldTo);
        this.lifeCrate = lifeCrate;
    }
}