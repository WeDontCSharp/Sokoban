package skeleton.model;
import skeleton.view.message.HealthStateChangeMessage;
import skeleton.view.message.ScoreStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;

public class WorkerWrapper extends Worker {
	
	public WorkerWrapper(Warehouse g, Field f, Direction dir, int idx) {
		super(g, f, dir, idx);
		this.getLevel().receiveMessage(new WorkerStepStateChangeMessage(this.getPlayerIndex(), Direction.Right, this.getCurField(), f, 0.0f));
	}

	@Override
	public void gainPoint() {
		super.gainPoint();
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new ScoreStateChangeMessage(this.getPlayerIndex(), this.getPoints()));
	}

	@Override
	public void losePoint() {
		super.losePoint();
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new ScoreStateChangeMessage(this.getPlayerIndex(), this.getPoints()));
	}
	
	@Override
	public void gainHealth() {
		super.gainHealth();
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new HealthStateChangeMessage(this.getPlayerIndex(), this.getHealth()));
	}

	@Override
	public void loseHealth() {
		super.loseHealth();
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new HealthStateChangeMessage(this.getPlayerIndex(), this.getHealth()));
	}
	
	@Override
	public void reSpawn() {
		super.reSpawn();
		// XXX: Not sure if works like this.
		//this.getLevel().receiveMessage(new WorkerStepStateChangeMessage(this.getPlayerIndex(), Direction.Right, this.getCurField(), this.getSpawnField(), 1.0f));
	}
	
	@Override
	public void die() {
		super.die();
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new HealthStateChangeMessage(this.getPlayerIndex(), 0));
	}

}
