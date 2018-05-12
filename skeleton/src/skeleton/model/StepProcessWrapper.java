package skeleton.model;

import skeleton.view.message.CrateStepStateChangeMessage;
import skeleton.view.message.LifeCrateStepStateChangeMessage;
import skeleton.view.message.StepStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;

public class StepProcessWrapper extends StepProcess {

	private StepStateChangeMessage msg;
	
	public StepProcessWrapper(Worker e, Field from, Field to) {
		super(e, from, to);
		this.msg = new WorkerStepStateChangeMessage(e.getPlayerIndex(), from, to, 0.0f);
	}
	
	public StepProcessWrapper(Crate e, Field from, Field to) {
		super(e, from, to);
		this.msg = new CrateStepStateChangeMessage(e, from, to, 0.0f);
	}
	
	public StepProcessWrapper(LifeCrate e, Field from, Field to) {
		super(e, from, to);
		this.msg = new LifeCrateStepStateChangeMessage(e, from, to, 0.0f);
	}
	
	@Override
	public void update() {
		super.update();
		this.msg.percent = this.getPercentage();
		this.getEntity().getLevel().receiveMessage(this.msg);
	}

}
