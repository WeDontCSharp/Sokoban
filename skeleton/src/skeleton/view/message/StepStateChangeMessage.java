package skeleton.view.message;

import skeleton.model.Field;

public abstract class StepStateChangeMessage extends StateChangeMessage {
    public final Field fieldFrom;	//A mezõ, melyikrõl a dolgozó lelépett.
    public final Field fieldTo;		//A mezõ, melyikre a dolgozó lép.
    public float percent;			//Megadja, hogy ány %-nál tart a process
	
    /**
     * Közös õs ami entitások kirajzolásáért felelõs.
     * 
     * @param type		Az állapotváltozás üzenet konkrét típusa
     * @param fieldFrom	A mezõ, melyikrõl a dolgozó lelépett.
     * @param fieldTo	A mezõ, melyikre a dolgozó lép.
     * @param percent	Megadja, hogy ány %-nál tart a process
     */
	public StepStateChangeMessage(StateChangeMessageType type, Field fieldFrom, Field fieldTo, float percent) {
		super(type);
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.percent = percent;
	}
	
}