package skeleton.view.message;

import skeleton.model.Field;

public abstract class FallStateChangeMessage extends StateChangeMessage {

	public final Field from;
	public final Field to;
	public float percent;
	
	/**
	 * 
	 * @param type		Az állapotváltozás üzenet konkrét típusa
	 * @param from		A mezõ, melyikrõl az entitás lekerül.
	 * @param to		A mezõ, melyikre az entitás kerül.
	 */
	public FallStateChangeMessage(StateChangeMessageType type, Field from, Field to) {
		super(type);
		this.from = from;
		this.to =  to;
		this.percent = 0.0f;
	}
	
}
