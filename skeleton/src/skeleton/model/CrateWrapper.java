package skeleton.model;
import skeleton.view.message.CrateFallStateChangeMessage;
import skeleton.view.message.CrateStepStateChangeMessage;

public class CrateWrapper extends Crate {
	
	public CrateWrapper(Warehouse g, Field f) {
		super(g, f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setCurField(Field f) {
		super.setCurField(f);
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new CrateStepStateChangeMessage(this, this.getCurField(), f));
	}
	
	@Override
	public void remove() {
		super.remove();
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new CrateFallStateChangeMessage(this));
	}

}
