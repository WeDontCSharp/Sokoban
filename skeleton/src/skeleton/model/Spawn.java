package skeleton.model;

import skeleton.meta.PrettyPrinter;

/**
 * A class representing a spawn. Spawns are only
 * reachable by their associated workers.
 * To other entities, they act like walls.
 */
public class Spawn extends Floor {

	/**
	 * The worker associated with this spawn.
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
	 * Tries to step a worker onto the spawn. If the non-owner is pushed against the spawn, in a chain, they lose a health point.
	 * @param firstPusher The worker who initiates the push.
     * @param w The worker being pushed to the spawn.
     * @return True if the worker is pushed into the spawn or he is associated with this spawn, otherwise false.
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

	/**
	 * Tries to step a crate onto the spawn.
	 * @param firstPusher The worker who initiates the push.
     * @param c The crate being pushed.
     * @return False as a crate cannot be pushed onto a spawn.
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
	
	/**
	 * Tells if there's an entity on the spawn.
     * @return True
	 */
    @Override
	public boolean isEmpty() {
		PrettyPrinter.startFunction("Spawn", "isEmpty()");
		PrettyPrinter.endFunction("Spawn", "isEmpty()", "true");
		return true;
	}

}
