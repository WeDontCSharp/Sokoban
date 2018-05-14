package skeleton.model;

public abstract class StepProcess implements Process {
	/**
	 * The speed of the movement.
	 */
	public static final float SPEED = 0.08f;
	/**
	 * The entity that is stepping.
	 */
	private Entity moving;
	/**
	 * The percent the process is currently at.
	 */
	private float percent;
	/**
	 * The field the entity is stepping from.
	 */
	private Field from;
	/**
	 * The field the entity is stepping to.
	 */
	private Field to;
	
	/**
	 * Creates a process.
	 * @param e The entity that is stepping.
	 * @param from The field the entity is stepping from.
	 * @param to The field the entity is stepping to.
	 */
	public StepProcess(Entity e, Field from, Field to) {
		this.percent = 0.0f;
		this.moving = e;
		this.from = from;
		this.to = to;
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
		
		this.to.setEntity(this.moving);
		this.moving.setCurField(this.to);
		
		this.moving.level.updateBlocking(to, true);
	}
	
	/**
	 * @return The percent the process is currently at.
	 */
	public float getPercentage() {
		return this.percent;
	}
	
	/**
	 * @return The entity that is currently stepping.
	 */
	public Entity getEntity() {
		return this.moving;
	}
	
}
