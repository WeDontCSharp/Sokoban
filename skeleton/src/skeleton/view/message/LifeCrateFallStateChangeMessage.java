package skeleton.view.message;

import skeleton.model.Field;
import skeleton.model.LifeCrate;

public class LifeCrateFallStateChangeMessage extends FallStateChangeMessage {
	public final LifeCrate lifeCrate;
	
	/**
	 * Egy sz�vecsk�s l�da lyukba es�s�t anim�lja.
	 * 
	 * @param from		A mez�, melyikr�l a sz�vecsk�s l�da leker�l.
	 * @param to		A mez�, melyikre a sz�vecsk�s l�da ker�l.
	 * @param crate		A leesett sz�vecsk�s l�da.
	 */
	public LifeCrateFallStateChangeMessage(LifeCrate lifeCrate, Field from, Field to) {
		super(StateChangeMessageType.LifeCrateFall, from, to);
        this.lifeCrate = lifeCrate;
	}
	
}