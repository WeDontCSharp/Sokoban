package skeleton.model;
import skeleton.view.message.HealthStateChangeMessage;
import skeleton.view.message.ScoreStateChangeMessage;
import skeleton.view.message.WorkerStepStateChangeMessage;

public class WorkerWrapper extends Worker {
	
	public WorkerWrapper(Warehouse g, Field f, Direction dir) {
		super(g, f, dir);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setCurField(Field f) {
		super.setCurField(f);
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new WorkerStepStateChangeMessage(this.getPlayerIndex(), this.getCurField(), f));
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
		this.getLevel().receiveMessage(new WorkerStepStateChangeMessage(this.getPlayerIndex(), this.getCurField(), this.getSpawnField()));
	}
	
	@Override
	public void die() {
		super.die();
		// XXX: Not sure if works like this.
		this.getLevel().receiveMessage(new HealthStateChangeMessage(this.getPlayerIndex(), 0));
	}

}
