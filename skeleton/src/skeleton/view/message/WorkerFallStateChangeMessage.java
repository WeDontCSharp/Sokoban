package skeleton.view.message;

import skeleton.model.Direction;
import skeleton.model.Field;

public class WorkerFallStateChangeMessage extends FallStateChangeMessage {
    public final int playerIndex;		//A leesett dolgozóhoz tartozó játékos indexe.
    public final Direction direction;
	
    /**
     * A játékos meghalását reprezentálja (lyukba esik).
     * 
     * @param playerIndex	A leesett dolgozóhoz tartozó játékos indexe.
     * @param dir			Az irány, melybe a dolgozót lépésre utasítja.
     * @param from			A mezõ, melyikrõl a dolgozó lekerül.
     * @param to			A mezõ, melyikre a dolgozó kerül.
     */
	public WorkerFallStateChangeMessage(int playerIndex, Direction dir, Field from, Field to) {
		super(StateChangeMessageType.WorkerFall, from, to);
        this.playerIndex = playerIndex;
        this.direction = dir;
	}
	
}