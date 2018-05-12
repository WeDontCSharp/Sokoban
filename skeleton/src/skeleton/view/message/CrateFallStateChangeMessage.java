package skeleton.view.message;

import skeleton.model.Crate;

public class CrateFallStateChangeMessage extends StateChangeMessage {
	public final Crate crate;
	
	public CrateFallStateChangeMessage(Crate crate) {
		super(StateChangeMessageType.CrateFall);
        this.crate = crate;
	}
	
}