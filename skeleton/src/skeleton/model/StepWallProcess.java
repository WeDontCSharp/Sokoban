package skeleton.model;

public abstract class StepWallProcess implements Process {
	/**
	 * The speed of the movement.
	 */
	public static final float SPEED = 0.08f;
	/**
	 * The worker that is beeing smashed by the wall.
	 */
	private Worker squashed;
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
	 * @param squashed The worker that is beeing smashed by the wall.
	 * @param from The field the worker is stepping from.
	 * @param to The field the worker is stepping to.
	 * @param removeTo The field where the worker should be place after the process.
	 */
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
	
	/**
	 * @return The percent the process is currently at.
	 */
	public Worker getEntity() {
		return this.squashed;
	}
	
	/**
	 * @return The entity that is currently stepping.
	 */
	public float getPercentage() {
		return this.percent;
	}
	
}
