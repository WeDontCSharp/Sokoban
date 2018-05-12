package skeleton.view.message;

import skeleton.model.Field;

public class WorkerStepStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;

    public WorkerStepStateChangeMessage(int playerIndex, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.WorkerStep, fieldFrom, fieldTo, perc);
        this.playerIndex = playerIndex;
    }
}