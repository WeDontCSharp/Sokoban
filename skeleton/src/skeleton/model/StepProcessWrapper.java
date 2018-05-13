package skeleton.model;

import skeleton.view.message.CrateStepStateChangeMessage;
import skeleton.view.message.LifeCrateStepStateChangeMessage;
import skeleton.view.message.StepStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class StepProcessWrapper extends StepProcess {

	/**
	 * A message telling an entity is stepping.
	 */
	private StepStateChangeMessage msg;
	
	/**
	 * Sends a message because a step process has been started.
	 * @param e The worker who is stepping.
	 * @param from The field the worker is stepping from.
	 * @param to The field the worker is stepping to.
	 */
	public StepProcessWrapper(Worker e, Field from, Field to) {
		super(e, from, to);
		this.msg = new WorkerStepStateChangeMessage(e.getPlayerIndex(), Field.getDirection(from, to), from, to, 0.0f);
	}
	
	/**
	 * Sends a message because a step process has been started.
	 * @param e The crate who is stepping.
	 * @param from The field the crate is stepping from.
	 * @param to The field the crate is stepping to.
	 */
	public StepProcessWrapper(Crate e, Field from, Field to) {
		super(e, from, to);
		this.msg = new CrateStepStateChangeMessage(e, from, to, 0.0f);
	}
	
	/**
	 * Sends a message because a step process has been started.
	 * @param e The lifecrate who is stepping.
	 * @param from The field the lifecrate is stepping from.
	 * @param to The field the lifecrate is stepping to.
	 */
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
