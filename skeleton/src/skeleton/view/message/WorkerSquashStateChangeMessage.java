package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerSquashStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;
    public final Direction direction;

    public WorkerSquashStateChangeMessage(int playerIndex, Direction dir, Field fieldFrom, Field fieldTo){
        super(StateChangeMessageType.WorkerSquash, fieldFrom, fieldTo, 0.0f);
        this.playerIndex = playerIndex;
        this.direction = dir;
    }
}
