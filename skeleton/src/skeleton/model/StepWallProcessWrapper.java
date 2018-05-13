package skeleton.model;

import skeleton.view.message.WorkerSquashStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class StepWallProcessWrapper extends StepWallProcess {

	private WorkerSquashStateChangeMessage msg;
	private WorkerStepStateChangeMessage stepMsg;
	
	public StepWallProcessWrapper(Worker squashed, Field from, Field to) {
		super(squashed, from, to, squashed.getSpawnField());
		
		this.msg = new WorkerSquashStateChangeMessage(squashed.getPlayerIndex(), Field.getDirection(from, to), from, to);
		this.stepMsg = new WorkerStepStateChangeMessage(squashed.getPlayerIndex(), Direction.Right, squashed.getSpawnField(), squashed.getSpawnField(), 1.0f);
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
		
		this.getEntity().getLevel().receiveMessage(this.stepMsg);
		this.msg.percent = 0.0f;
		this.getEntity().getLevel().receiveMessage(this.msg);
	}
	
}
