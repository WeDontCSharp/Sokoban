package skeleton.view.message;

import skeleton.model.LifeCrate;

public class LifeCrateFallStateChangeMessage extends StateChangeMessage {
	public final LifeCrate lifeCrate;
	
	public LifeCrateFallStateChangeMessage(LifeCrate lifeCrate) {
		super(StateChangeMessageType.LifeCrateFall);
        this.lifeCrate = lifeCrate;
	}
	
}