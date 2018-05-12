package skeleton.view.message;

import skeleton.model.Field;
import skeleton.model.LifeCrate;

public class LifeCrateStepStateChangeMessage extends StepStateChangeMessage {
    public final LifeCrate lifeCrate;

    public LifeCrateStepStateChangeMessage(LifeCrate lifeCrate, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.LifeCrateStep, fieldFrom, fieldTo, perc);
        this.lifeCrate = lifeCrate;
    }
}