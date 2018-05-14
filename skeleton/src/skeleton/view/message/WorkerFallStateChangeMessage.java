package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerFallStateChangeMessage extends FallStateChangeMessage {
    public final int playerIndex;		//A leesett dolgoz�hoz tartoz� j�t�kos indexe.
    public final Direction direction;
	
    /**
     * A j�t�kos meghal�s�t reprezent�lja (lyukba esik).
     * 
     * @param playerIndex	A leesett dolgoz�hoz tartoz� j�t�kos indexe.
     * @param dir			Az ir�ny, melybe a dolgoz�t l�p�sre utas�tja.
     * @param from			A mez�, melyikr�l a dolgoz� leker�l.
     * @param to			A mez�, melyikre a dolgoz� ker�l.
     */
	public WorkerFallStateChangeMessage(int playerIndex, Direction dir, Field from, Field to) {
		super(StateChangeMessageType.WorkerFall, from, to);
        this.playerIndex = playerIndex;
        this.direction = dir;
	}
	
}