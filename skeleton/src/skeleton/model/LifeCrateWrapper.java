package skeleton.model;
import skeleton.view.message.LifeCrateFallStateChangeMessage;
import skeleton.view.message.LifeCrateStepStateChangeMessage;

public class LifeCrateWrapper extends LifeCrate {
	
	public LifeCrateWrapper(Warehouse g, Field f) {
		super(g, f);
		// TODO Auto-generated constructor stub
		level.receiveMessage(new LifeCrateStepStateChangeMessage(this, f, f, 1.0f));
	}
	
	@Override
	public void remove(Worker firstPusher) {
		super.remove(firstPusher);
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new LifeCrateFallStateChangeMessage(this));
	}

}
