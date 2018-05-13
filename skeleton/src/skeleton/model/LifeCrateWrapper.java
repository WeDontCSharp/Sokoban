package skeleton.model;
import skeleton.view.message.LifeCrateFallStateChangeMessage;
import skeleton.view.message.LifeCrateStepStateChangeMessage;

public class LifeCrateWrapper extends LifeCrate {
	
	public LifeCrateWrapper(Warehouse g, Field f) {
		super(g, f);
		// TODO Auto-generated constructor stub
		level.receiveMessage(new LifeCrateStepStateChangeMessage(this, f, f, 1.0f));
	}

}
