package skeleton.view.message;

import skeleton.model.Crate;
import skeleton.model.Field;

public class CrateFallStateChangeMessage extends FallStateChangeMessage {
	public final Crate crate;
	
	/**
	 * Egy l�da lyukba es�s�t anim�lja.
	 * 
	 * @param from		A mez�, melyikr�l a l�da leker�l.
	 * @param to		A mez�, melyikre a l�da ker�l.
	 * @param crate		A leesett l�da.
	 */
	public CrateFallStateChangeMessage(Field from, Field to, Crate crate) {
		super(StateChangeMessageType.CrateFall, from, to);
        this.crate = crate;
	}
	
}