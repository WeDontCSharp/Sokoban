package skeleton.model;

import skeleton.meta.PrettyPrinter;

/**
 * A class representing a wall.
 * Entities cannot step into walls
 * but a worker may be pushed into one.
 */
public class Wall extends Field  {
    
    /**
	 * Creates a wall.
	 * @param level The warehouse to the create the floor in.
	 */
    public Wall(Warehouse level) {
		super(level);
	}
	
    /**
	 * Tries to step the worker into the wall. If the worker is pushed against the wall, in a chain, they lose a health point.
	 * @param firstPusher The worker who initiates the push.
     * @param w The worker being pushed.
     * @return True if the worker is pushed in a chain into the wall, otherwise false.
	 */
	@Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Wall", "visitByWorker(firstPusher, w)");
		if (firstPusher != w) {
			w.loseHealth();
			PrettyPrinter.endFunction("Wall", "visitByWorker(firstPusher, w)", "true");
			return true;
		}
		else {
			PrettyPrinter.endFunction("Wall", "visitByWorker(firstPusher, w)", "false");
			return false;
		}
	}

	/**
	 * Tries to step the crate into the wall.
	 * @param firstPusher The worker who initiates the push.
     * @param c The crate being pushed.
     * @return False because a crate cannot be pushed into the wall.
	 */
    @Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Wall", "visitByCrate(firstPusher, c)");
		PrettyPrinter.endFunction("Wall", "visitByCrate(firstPusher, c)", "false");
		return false;
	}

}

