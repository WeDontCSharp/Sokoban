package skeleton.model;
import skeleton.view.message.CrateFallStateChangeMessage;
import skeleton.view.message.CrateStepStateChangeMessage;

public class CrateWrapper extends Crate {
	
	public CrateWrapper(Warehouse g, Field f) {
		super(g, f);
		// TODO Auto-generated constructor stub
		this.getLevel().receiveMessage(new CrateStepStateChangeMessage(this, this.getCurField(), this.getCurField(), 1.0f));
	}

}
