package skeleton.view.message;

import skeleton.model.Field;

public abstract class StepStateChangeMessage extends StateChangeMessage {
    public final Field fieldFrom;	//A mez�, melyikr�l a dolgoz� lel�pett.
    public final Field fieldTo;		//A mez�, melyikre a dolgoz� l�p.
    public float percent;			//Megadja, hogy �ny %-n�l tart a process
	
    /**
     * K�z�s �s ami entit�sok kirajzol�s��rt felel�s.
     * 
     * @param type		Az �llapotv�ltoz�s �zenet konkr�t t�pusa
     * @param fieldFrom	A mez�, melyikr�l a dolgoz� lel�pett.
     * @param fieldTo	A mez�, melyikre a dolgoz� l�p.
     * @param percent	Megadja, hogy �ny %-n�l tart a process
     */
	public StepStateChangeMessage(StateChangeMessageType type, Field fieldFrom, Field fieldTo, float percent) {
		super(type);
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.percent = percent;
	}
	
}