package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerStepStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;		//A lépõ dolgozó indexe.
    public final Direction direction;	//A léptetés iránya
    
    /**
     * Egy dolgozót rajzol ki a felületen egy megadott pozícióba, ezzel animálva egy lépést.
     * 
     * @param playerIndex 		A lépõ dolgozó indexe.
     * @param dir				A léptetés iránya
     * @param fieldFrom			A mezõ, melyikrõl a dolgozó lelépett.
     * @param fieldTo			A mezõ, melyikre a dolgozó lép.
     * @param perc				Az állapotváltozás üzenet konkrét típusa
     */
    public WorkerStepStateChangeMessage(int playerIndex, Direction dir, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.WorkerStep, fieldFrom, fieldTo, perc);
        this.playerIndex = playerIndex;
        this.direction = dir;
    }
}