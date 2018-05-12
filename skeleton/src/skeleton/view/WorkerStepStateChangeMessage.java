package skeleton.view;

import skeleton.model.Field;

public class WorkerStepStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;

    public WorkerStepStateChangeMessage(int playerIndex, Field fieldFrom, Field fieldTo){
        super(StateChangeMessageType.WorkerStep, fieldFrom, fieldTo);
        this.playerIndex = playerIndex;
    }
}