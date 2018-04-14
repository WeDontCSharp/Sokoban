package skeleton.model;

import skeleton.meta.PrettyPrinter;

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
	
	/**
	 * Creates a spawn.
	 * @param level The warehouse to the create the spawn in.
     * @param owner The owner associated with this spawn.
	 */
    public Spawn(Warehouse level, Worker owner) {
		super(level);
		this.owner = owner;
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
		PrettyPrinter.startFunction("Floor", "visitByWorker(firstPusher, w)");
		if (owner == null) {
			Boolean ans = PrettyPrinter.askQuestion("Is the pushed worker the owner of this spawn? [Y - yes, N - no] : ", 
					"YN", new Boolean[] { true, false
			});
			if (ans.booleanValue()) {
				this.owner = w;
			}
		}
		if (w != firstPusher && w != owner) {
			w.loseHealth();
			PrettyPrinter.endFunction("Floor", "visitByWorker(firstPusher, w)", "true");
			return true;
		}
		else if (w == owner) {
			PrettyPrinter.endFunction("Floor", "visitByWorker(firstPusher, w)", "true");
			return true;
		}
		else {
			PrettyPrinter.endFunction("Floor", "visitByWorker(firstPusher, w)", "false");
			return false;
		}
	}

    /* (non-Javadoc)
     * @see skeleton.model.Floor#visitByCrate(skeleton.model.Worker, skeleton.model.Crate)
     */
    @Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Floor", "visitByCrate(firstPusher, c)");
		PrettyPrinter.endFunction("Floor", "visitByCrate(firstPusher, c)", "false");
		return false;
	}
	
	/**
	 * Getter function for the spawn's associated worker.
     * @return The owner.
	 */
    public Worker getOwner() {
		PrettyPrinter.startFunction("Spawn", "getOwner()");
		PrettyPrinter.endFunction("Spawn", "getOwner()", "owner");
		return owner;
	}
	
    /* (non-Javadoc)
     * @see skeleton.model.Field#isEmpty()
     */
    @Override
	public boolean isEmpty() {
		PrettyPrinter.startFunction("Spawn", "isEmpty()");
		PrettyPrinter.endFunction("Spawn", "isEmpty()", "true");
		return true;
	}

}
