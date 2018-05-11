package skeleton.view;

import skeleton.model.LifeCrate;

public class LifeCrateStepStateChangeMessage extends StepStateChangeMessage {
    public final LifeCrate lifeCrate;

    public LifeCrateStepStateChangeMessage(LifeCrate lifeCrate){
        super(StateChangeMessageType.LifeCrateStep);
        this.lifeCrate = lifeCrate;
    }
}