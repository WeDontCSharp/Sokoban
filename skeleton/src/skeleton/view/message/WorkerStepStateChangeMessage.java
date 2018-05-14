package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerStepStateChangeMessage extends StepStateChangeMessage {
    public final int playerIndex;		//A l�p� dolgoz� indexe.
    public final Direction direction;	//A l�ptet�s ir�nya
    
    /**
     * Egy dolgoz�t rajzol ki a fel�leten egy megadott poz�ci�ba, ezzel anim�lva egy l�p�st.
     * 
     * @param playerIndex 		A l�p� dolgoz� indexe.
     * @param dir				A l�ptet�s ir�nya
     * @param fieldFrom			A mez�, melyikr�l a dolgoz� lel�pett.
     * @param fieldTo			A mez�, melyikre a dolgoz� l�p.
     * @param perc				Az �llapotv�ltoz�s �zenet konkr�t t�pusa
     */
    public WorkerStepStateChangeMessage(int playerIndex, Direction dir, Field fieldFrom, Field fieldTo, float perc){
        super(StateChangeMessageType.WorkerStep, fieldFrom, fieldTo, perc);
        this.playerIndex = playerIndex;
        this.direction = dir;
    }
}