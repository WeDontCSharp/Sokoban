package skeleton.model;

public abstract class StepWallProcess implements Process {
	public static final float SPEED = 0.08f;
	
	private Worker squashed;
	private float percent;
	private Field from;
	private Field to;
	private Field removeTo;
	
	public StepWallProcess(Worker squashed, Field from, Field to, Field removeTo) {
		this.squashed = squashed;
		this.percent = 0.0f;
		this.from = from;
		this.to = to;
		this.removeTo = removeTo;
	}
	
	@Override
	public void start() {
		this.from.lock();
		this.to.lock();
		
		from.unsetEntity();
	}

	@Override
	public void update() {
		percent += SPEED;
		if (percent >= 1.0f) {
			percent = 1.0f;
		}
	}

	@Override
	public boolean isOver() {
		return percent >= 1.0f;
	}

	@Override
	public void end() {
		this.from.unlock();
		this.to.unlock();
		
		this.removeTo.setEntity(this.squashed);
		this.squashed.setCurField(this.removeTo);
	}
	
	public Worker getEntity() {
		return this.squashed;
	}
	
	public float getPercentage() {
		return this.percent;
	}
	
}
