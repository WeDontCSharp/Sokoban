package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerStepStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;
    public final Direction direction;

    public WorkerStepStateChangeMessage(int playerIndex, Direction dir, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.WorkerStep, fieldFrom, fieldTo, perc);
        this.playerIndex = playerIndex;
        this.direction = dir;
    }
}