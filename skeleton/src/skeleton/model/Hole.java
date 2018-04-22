package skeleton.model;

/**
 * A class representing a hole. A hole is always
 * reachable by the entities. If the hole is open 
 * and an entity steps on it, it falls down.
 */
public class Hole extends Floor {
	
	/**
	 * The state of the hole.
	 */
	private boolean open = true;
	/**
	 * The worker who pushed a crate on the hole.
	 */
	private Worker whoPushed;
	
	/**
	 * Creates a hole.
	 * @param level The warehouse to the create the hole in.
	 */
	public Hole(Warehouse level) {
		super(level);
	}
	
	/**
	 * Sets the slipperiness of a hole.
	 * @param slipFactor The new value that replaces the current factor.
	 * @return False, because the slipFactor of a hole can never be changed.
	 */
	public boolean placeSlipFactor(double slipFactor) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see skeleton.model.Floor#visitByWorker(skeleton.model.Worker, skeleton.model.Worker)
	 */
	@Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		if (isOpen()) {
			w.loseHealth();
		}
		else {
			super.setEntity(w);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see skeleton.model.Floor#visitByCrate(skeleton.model.Worker, skeleton.model.Crate)
	 */
	@Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		whoPushed = firstPusher;
		if (isOpen()) {
			c.remove();
		}
		else {
			super.setEntity(c);
		}
		super.getLevel().updateBlocking(this, true);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see skeleton.model.Field#visitByLifeCrate(skeleton.model.Worker, skeleton.model.LifeCrate)
	 */
	@Override
	public boolean visitByLifeCrate(Worker firstPusher, LifeCrate lc) {
		whoPushed = firstPusher;
		if (isOpen()) {
			lc.remove(firstPusher);
		}
		else {
			super.setEntity(lc);
		}
		super.getLevel().updateBlocking(this, true);
		return true;
	}
	
	/**
	 * Tells whether the hole is open or not.
	 * @return True if the hole is open, false otherwise.
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Opens or closes the hole.
	 * @param open True, if the hole is intended to be opened, false if closed.
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	/**
	 * If a crate is standing on the hole, it gets the worker who pushed it.
	 * @return The worker.
	 */
	public Worker getWhoPushed() {
		return whoPushed;
	}
}
