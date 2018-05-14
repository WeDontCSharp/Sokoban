package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerSquashStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;
    public final Direction direction;
    
    /**
     * A játékos meghalását reprezentálja (falnak nyomódik)
     * 
     * @param playerIndex	A lépésre utasított munkás indexe.
     * @param dir			Az irány, melybe a munkást lépésre utasítja.
     * @param fieldFrom		A mezõ, melyikrõl a dolgozó lekerül.
     * @param fieldTo		A mezõ, melyikre a dolgozó kerül.
     */
    public WorkerSquashStateChangeMessage(int playerIndex, Direction dir, Field fieldFrom, Field fieldTo){
        super(StateChangeMessageType.WorkerSquash, fieldFrom, fieldTo, 0.0f);
        this.playerIndex = playerIndex;
        this.direction = dir;
    }
}
