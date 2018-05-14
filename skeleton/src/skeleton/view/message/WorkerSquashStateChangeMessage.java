package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerSquashStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;
    public final Direction direction;
    
    /**
     * A j�t�kos meghal�s�t reprezent�lja (falnak nyom�dik)
     * 
     * @param playerIndex	A l�p�sre utas�tott munk�s indexe.
     * @param dir			Az ir�ny, melybe a munk�st l�p�sre utas�tja.
     * @param fieldFrom		A mez�, melyikr�l a dolgoz� leker�l.
     * @param fieldTo		A mez�, melyikre a dolgoz� ker�l.
     */
    public WorkerSquashStateChangeMessage(int playerIndex, Direction dir, Field fieldFrom, Field fieldTo){
        super(StateChangeMessageType.WorkerSquash, fieldFrom, fieldTo, 0.0f);
        this.playerIndex = playerIndex;
        this.direction = dir;
    }
}
