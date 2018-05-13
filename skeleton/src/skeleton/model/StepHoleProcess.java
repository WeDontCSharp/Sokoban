package skeleton.model;

public abstract class StepHoleProcess implements Process {
	public static final float SPEED = 0.08f;
	
	private Entity falling;
	private float percent;
	private Field from;
	private Field to;
	
	public StepHoleProcess(Entity falling, Field from, Field to) {
		this.falling = falling;
		this.percent = 0.0f;
		this.from = from;
		this.to = to;
	}
	
	@Override
	public void start() {
		this.from.lock();
		this.to.lock();
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
	}
	
	public float getPercentage() {
		return this.percent;
	}
	
	public Entity getEntity() {
		return this.falling;
	}
	
}
