package skeleton.view.message;

import skeleton.model.Field;
import skeleton.model.LifeCrate;

public class LifeCrateFallStateChangeMessage extends FallStateChangeMessage {
	public final LifeCrate lifeCrate;
	
	/**
	 * Egy szívecskés láda lyukba esését animálja.
	 * 
	 * @param from		A mezõ, melyikrõl a szívecskés láda lekerül.
	 * @param to		A mezõ, melyikre a szívecskés láda kerül.
	 * @param crate		A leesett szívecskés láda.
	 */
	public LifeCrateFallStateChangeMessage(LifeCrate lifeCrate, Field from, Field to) {
		super(StateChangeMessageType.LifeCrateFall, from, to);
        this.lifeCrate = lifeCrate;
	}
	
}