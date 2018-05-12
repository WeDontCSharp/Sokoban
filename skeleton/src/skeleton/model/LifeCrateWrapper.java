package skeleton.model;
import skeleton.view.LifeCrateFallStateChangeMessage;
import skeleton.view.LifeCrateStepStateChangeMessage;

public class LifeCrateWrapper extends LifeCrate {
	
	public LifeCrateWrapper(Warehouse g, Field f) {
		super(g, f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setCurField(Field f) {
		super.setCurField(f);
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new LifeCrateStepStateChangeMessage(this, this.getCurField(), f));
	}
	
	@Override
	public void remove(Worker firstPusher) {
		super.remove(firstPusher);
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new LifeCrateFallStateChangeMessage(this));
	}

}
