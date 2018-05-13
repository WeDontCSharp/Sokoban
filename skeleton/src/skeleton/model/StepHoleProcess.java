package skeleton.model;

/**
 * A process representing a step onto a hole.
 */
public abstract class StepHoleProcess implements Process {
	/**
	 * The speed of the movement.
	 */
	public static final float SPEED = 0.08f;
	/**
	 * The entity that steps onto the hole.
	 */
	private Entity falling;
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
	 * The field where the entity should be place after the process.
	 */
	private Field removeTo;
	
	/**
	 * Creates a process.
	 * @param falling The entity that steps onto the hole.
	 * @param from The field the entity is stepping from.
	 * @param to The field the entity is stepping to.
	 * @param removeTo The field where the entity should be place after the process.
	 */
	public StepHoleProcess(Entity falling, Field from, Field to, Field removeTo) {
		this.falling = falling;
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
		
		if (this.removeTo != null) {
			this.removeTo.setEntity(this.falling);
			this.falling.setCurField(this.removeTo);
		}
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
		return this.falling;
	}
	
}
