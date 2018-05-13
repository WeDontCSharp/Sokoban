package skeleton.view.message;

import skeleton.model.Field;
import skeleton.model.LifeCrate;

public class LifeCrateFallStateChangeMessage extends FallStateChangeMessage {
	public final LifeCrate lifeCrate;
	
	public LifeCrateFallStateChangeMessage(LifeCrate lifeCrate, Field from, Field to) {
		super(StateChangeMessageType.LifeCrateFall, from, to);
        this.lifeCrate = lifeCrate;
	}
	
}