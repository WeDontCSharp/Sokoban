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
	 * Sets the slipperiness of a wall.
	 * @param slipFactor The new value that replaces the current factor.
	 * @return False, because the slipFactor of a wall can never be changed.
	 */
	public boolean placeSlipFactor(double slipFactor) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see skeleton.model.IVisitable#visitByWorker(skeleton.model.Worker, skeleton.model.Worker)
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

    /* (non-Javadoc)
     * @see skeleton.model.IVisitable#visitByCrate(skeleton.model.Worker, skeleton.model.Crate)
     */
    @Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Wall", "visitByCrate(firstPusher, c)");
		PrettyPrinter.endFunction("Wall", "visitByCrate(firstPusher, c)", "false");
		return false;
	}

}

