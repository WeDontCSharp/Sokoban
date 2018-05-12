package skeleton.model;

/**
 * A class representing a spawn. Spawns are only
 * reachable by their associated workers.
 * To other entities, they act like walls.
 */
public class Spawn extends Floor {

	/**
	 * The worker associated with this spawn, beeing the only one to step here.
	 */
    private Worker owner;
	
	public void setOwner(Worker owner) {
		this.owner = owner;
		owner.setSpawnField(this);
	}

	/**
	 * Creates a spawn.
	 * @param level The warehouse to the create the spawn in.
	 */
    public Spawn(Warehouse level, int x, int y) {
		super(level, x, y);
	}
    
    /**
	 * Sets the slipperiness of a spawn.
	 * @param slipFactor The new value that replaces the current factor.
	 * @return False, because the slipFactor of a spawn can never be changed.
	 */
	public boolean placeSlipFactor(double slipFactor) {
		return false;
	}

    /* (non-Javadoc)
     * @see skeleton.model.Floor#visitByWorker(skeleton.model.Worker, skeleton.model.Worker)
     */
    @Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		if (w != firstPusher && w != owner) {
			w.loseHealth();
			return true;
		}
		else if (w == owner) {
			return true;
		}
		else {
			return false;
		}
	}

    /* (non-Javadoc)
     * @see skeleton.model.Floor#visitByCrate(skeleton.model.Worker, skeleton.model.Crate)
     */
    @Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		return false;
	}
	
	/**
	 * Getter function for the spawn's associated worker.
     * @return The owner.
	 */
    public Worker getOwner() {
		return owner;
	}
	
    /* (non-Javadoc)
     * @see skeleton.model.Field#isEmpty()
     */
    @Override
	public boolean isEmpty() {
		return true;
	}
    
    @Override
    public boolean isBlocking() {
    	return true;
    }

}
