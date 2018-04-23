package skeleton.model;

public class LoseLifeProcess implements Process {
	public static final float SPEED = 0.08f;
	
	private float percent;
	private Entity moving;
	private Field from;
	private Field to;
	
	public LoseLifeProcess(Entity e, Field from, Field to) {
		this.percent = 0.0f;
		this.moving = e;
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
	
}
