package skeleton.view;

import skeleton.model.Field;
import skeleton.model.LifeCrate;

public class LifeCrateStepStateChangeMessage extends StepStateChangeMessage {
    public final LifeCrate lifeCrate;

    public LifeCrateStepStateChangeMessage(LifeCrate lifeCrate, Field fieldFrom, Field fieldTo, int process){
        super(StateChangeMessageType.LifeCrateStep, fieldFrom, fieldTo, process);
        this.lifeCrate = lifeCrate;
    }
}