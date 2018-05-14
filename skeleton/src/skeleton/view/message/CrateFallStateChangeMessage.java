package skeleton.view.message;

import skeleton.model.Crate;
import skeleton.model.Field;

public class CrateFallStateChangeMessage extends FallStateChangeMessage {
	public final Crate crate;
	
	/**
	 * Egy láda lyukba esését animálja.
	 * 
	 * @param from		A mezõ, melyikrõl a láda lekerül.
	 * @param to		A mezõ, melyikre a láda kerül.
	 * @param crate		A leesett láda.
	 */
	public CrateFallStateChangeMessage(Field from, Field to, Crate crate) {
		super(StateChangeMessageType.CrateFall, from, to);
        this.crate = crate;
	}
	
}