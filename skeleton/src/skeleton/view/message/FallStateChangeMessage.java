package skeleton.view.message;

import skeleton.model.Field;

public abstract class FallStateChangeMessage extends StateChangeMessage {

	public final Field from;
	public final Field to;
	public float percent;
	
	/**
	 * 
	 * @param type		Az �llapotv�ltoz�s �zenet konkr�t t�pusa
	 * @param from		A mez�, melyikr�l az entit�s leker�l.
	 * @param to		A mez�, melyikre az entit�s ker�l.
	 */
	public FallStateChangeMessage(StateChangeMessageType type, Field from, Field to) {
		super(type);
		this.from = from;
		this.to =  to;
		this.percent = 0.0f;
	}
	
}
