package skeleton.model;

/**
 * A class representing a crate.
 */
public abstract class Crate extends Entity {
	private boolean onTarget;
	
	/**
	 * Crate's construstor.
	 * 
	 * @param g Warehouse, where the entities are listed and specifies the level.
	 * @param f	The field, where the create appears.
	 */
	public Crate(Warehouse g, Field f) {
		super(g, f);
	}

	@Override
	public boolean push(Worker firstPusher, Entity pushed, Direction dir) {
		return pushed.pushByCrate(firstPusher, this, dir);
	}

	@Override
	public boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir) {
		double remPower = firstPusher.consumePower(getCurField().getSlipFactor() * getWeight());
		if (remPower < 0) {
			return false;
		}
		return step(firstPusher, dir);
	}

	@Override
	public boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir) {
		double remPower = firstPusher.consumePower(getCurField().getSlipFactor() * getWeight());
		if (remPower < 0) {
			return false;
		}
		return step(firstPusher, dir);
	}
	
	/**
	 * Removes the Crate from the game.
	 */
	public void remove() {
		// XXX: Stub
	}

	/**
	 * Tells whether a crate is stuck or not.
	 * @return
	 */
	public boolean isStuck() {
		return this.getLevel().getBlocking(getCurField());
		// XXX: Stub
	}

	/**
	 * Tells whether a crate is on a target field.
	 * @return
	 */
	public boolean isOnTarget() {
		return this.onTarget;
		// XXX: Stub
	}
	
	/**
	 * Sets whether the crate is on a target field or not.
	 * @param ot True if the crate is on a target, false otherwise.
	 */
	public void setOntarget(boolean ot) {
		this.onTarget = ot;
	}

	@Override
	public boolean visit(Worker firstPusher, IVisitable iv) {
		return iv.visitByCrate(firstPusher, this);
	}
	
	@Override
	public void startStepProcess(Field to) {
		this.pushProcess(new StepProcessWrapper(this, this.getCurField(), to));
	}
	
	@Override
	public void startStepHoleProcess(Field to) {
		this.pushProcess(new StepHoleProcessWrapper(this, this.getCurField(), to));
	}
	
}
