package skeleton.model;
import skeleton.view.message.HealthStateChangeMessage;
import skeleton.view.message.ScoreStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;

/**
 * Overrides the methods which affect the display
 * and sends messages so that the View could handle the changes.
 */
public class WorkerWrapper extends Worker {
	
	/**
	 * Sends a message because a worker has been created.
	 * @param g The warehouse to create the worker in.
	 * @param f The field to place the worker onto.
	 * @param dir The direction the worker is facing.
	 * @param idx The playerIndex of the worker.
	 * 
	 */
	public WorkerWrapper(Warehouse g, Field f, Direction dir, int idx) {
		super(g, f, dir, idx);
		this.getLevel().receiveMessage(new WorkerStepStateChangeMessage(this.getPlayerIndex(), Direction.Right, this.getCurField(), f, 0.0f));
	}

	@Override
	public void gainPoint() {
		super.gainPoint();
		this.getLevel().receiveMessage(new ScoreStateChangeMessage(this.getPlayerIndex(), this.getPoints()));
	}

	@Override
	public void losePoint() {
		super.losePoint();
		this.getLevel().receiveMessage(new ScoreStateChangeMessage(this.getPlayerIndex(), this.getPoints()));
	}
	
	@Override
	public void gainHealth() {
		super.gainHealth();
		this.getLevel().receiveMessage(new HealthStateChangeMessage(this.getPlayerIndex(), this.getHealth()));
	}

	@Override
	public void loseHealth() {
		super.loseHealth();
		this.getLevel().receiveMessage(new HealthStateChangeMessage(this.getPlayerIndex(), this.getHealth()));
	}
	
	@Override
	public void reSpawn() {
		super.reSpawn();
	}
	
	@Override
	public void die() {
		super.die();
		this.getLevel().receiveMessage(new HealthStateChangeMessage(this.getPlayerIndex(), 0));
	}

}
