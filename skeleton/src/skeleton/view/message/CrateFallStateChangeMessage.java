package skeleton.view.message;

import skeleton.model.Crate;
import skeleton.model.Field;

public class CrateFallStateChangeMessage extends FallStateChangeMessage {
	public final Crate crate;
	
	public CrateFallStateChangeMessage(Field from, Field to, Crate crate) {
		super(StateChangeMessageType.CrateFall, from, to);
        this.crate = crate;
	}
	
}