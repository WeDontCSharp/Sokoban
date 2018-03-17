package skeleton.model;

import skeleton.meta.PrettyPrinter;

/**
 * A class representing a floor. A floor is always
 * reachable by the entities.
 */
public class Floor extends Field {

	/**
	 * Creates a floor.
	 * @param level The warehouse to the create the floor in.
	 */
	public Floor(Warehouse level) {
		super(level);
	}

	@Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Floor", "visitByWorker(firstPusher, w)");
		super.setEntity(w);
		PrettyPrinter.endFunction("Floor", "visitByWorker(firstPusher, w)", "true");
		return true;
	}

	@Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Floor", "visitByCrate(firstPusher, c)");
		super.setEntity(c);
		PrettyPrinter.endFunction("Floor", "visitByCrate(firstPusher, c)", "true");
		return true;
	}

}
