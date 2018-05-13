package skeleton.model;

import skeleton.view.message.CrateFallStateChangeMessage;
import skeleton.view.message.FallStateChangeMessage;
import skeleton.view.message.LifeCrateFallStateChangeMessage;
import skeleton.view.message.StepStateChangeMessage;
import skeleton.view.message.WorkerFallStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class StepHoleProcessWrapper extends StepHoleProcess {

	private FallStateChangeMessage msg;
	private StepStateChangeMessage stepMsg;
	
	public StepHoleProcessWrapper(Worker e, Field from, Field to) {
		super(e, from, to, e.getSpawnField());
		this.msg = new WorkerFallStateChangeMessage(e.getPlayerIndex(), Field.getDirection(from, to), from, to);
		this.stepMsg = new WorkerStepStateChangeMessage(e.getPlayerIndex(), Field.getDirection(from, to), from, e.getSpawnField(), 1.0f);
	}
	
	public StepHoleProcessWrapper(Crate e, Field from, Field to) {
		super(e, from, to, null);
		this.msg = new CrateFallStateChangeMessage(from, to, e);
		this.stepMsg = null;
	}
	
	public StepHoleProcessWrapper(LifeCrate e, Field from, Field to) {
		super(e, from, to, null);
		this.msg = new LifeCrateFallStateChangeMessage(e, from, to);
		this.stepMsg = null;
	}
	
	@Override
	public void update() {
		super.update();
		this.msg.percent = this.getPercentage();
		this.getEntity().getLevel().receiveMessage(this.msg);
	}
	
	@Override
	public void end() {
		super.end();
		if (this.stepMsg != null) {
			this.msg.percent = 0.0f;
			this.getEntity().getLevel().receiveMessage(this.msg);
			this.getEntity().getLevel().receiveMessage(this.stepMsg);
		}
	}
	
}
