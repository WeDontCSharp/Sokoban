package skeleton.model;

import skeleton.meta.PrettyPrinter;

/**
 * A class representing a crate.
 */
public class Crate extends Entity {
	
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
		PrettyPrinter.startFunction("Crate", "push(firstPusher, pushed, dir)");
		boolean res = pushed.pushByCrate(firstPusher, this, dir);
		PrettyPrinter.endFunction("Crate", "push(firstPusher, pushed, dir)", res ? "true" : "false");
		return res;
	}

	@Override
	public boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir) {
		PrettyPrinter.startFunction("Crate", "pushByWorker(firstPusher, pusher, dir)");
		boolean res = step(firstPusher, dir);
		PrettyPrinter.endFunction("Crate", "pushByWorker(firstPusher, pusher, dir)", res ? "true" : "false");
		return res;
	}

	@Override
	public boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir) {
		PrettyPrinter.startFunction("Crate", "pushByCrate(firstPusher, pusher, dir)");
		boolean res = step(firstPusher, dir);
		PrettyPrinter.endFunction("Crate", "pushByCrate(firstPusher, pusher, dir)", res ? "true" : "false");
		return res;
	}
	
	/**
	 * Removes the Crate from the game.
	 */
	public void remove() {
		PrettyPrinter.startFunction("Crate", "remove()");
		// XXX: Stub
		PrettyPrinter.endFunction("Crate", "remove()");
	}

	@Override
	public boolean visit(Worker firstPusher, IVisitable iv) {
		PrettyPrinter.startFunction("Crate", "visit(firstPusher, iv)");
		boolean res = iv.visitByCrate(firstPusher, this);
		PrettyPrinter.endFunction("Crate", "visit(firstPusher, iv)", res ? "true" : "false");
		return res;
	}
	
}
