package skeleton.model;

import skeleton.meta.PrettyPrinter;

/**
 * A class representing a worker.
 */
public class Worker extends Entity {
	private int points;
	private int health;
	private Field spawnField;
	
	/**
	 * Worker's constructor. The generated worker appears ion it's spawner field,
	 *  which is specified in the constuctor.
	 * 
	 * @param g 	Warehouse, where the entities are listed and specifies the level.
	 * @param f 	The new worker's spawnField, spawns here immediately.
	 * @param dir 	The direction, where the worker is heading.
	 */
	public Worker(Warehouse g, Field f, Direction dir) {
		super(g, f);
		this.spawnField = f;
	}
	
	/**
	 * Moving the Worker. Moves the worker one step in the specified direction. 
	 * 
	 * @param dir 	Direction of the moving.
	 */
	public void move(Direction dir) {
		PrettyPrinter.startFunction("Worker", "move(dir)");
		step(this, dir);
		PrettyPrinter.endFunction("Worker", "move(dir)");
	}
	
	/* (non-Javadoc)
	 * @see skeleton.model.Entity#push(skeleton.model.Worker, skeleton.model.Entity, skeleton.model.Direction)
	 */
	@Override
	public boolean push(Worker firstPusher, Entity pushed, Direction dir) {
		PrettyPrinter.startFunction("Worker", "push(firstPusher, pushed, dir)");
		boolean res = pushed.pushByWorker(firstPusher, this, dir);
		PrettyPrinter.endFunction("Worker", "push(firstPusher, pushed, dir)", res ? "true" : "false");
		return res;
	}

	/* (non-Javadoc)
	 * @see skeleton.model.Entity#pushByWorker(skeleton.model.Worker, skeleton.model.Worker, skeleton.model.Direction)
	 */
	@Override
	public boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir) {
		PrettyPrinter.startFunction("Worker", "pushByWorker(firstPusher, pusher, dir)");
		if (firstPusher == pusher) {
			PrettyPrinter.endFunction("Worker", "pushByWorker(firstPusher, pusher, dir)", "false");
			return false;
		}
		boolean res = step(firstPusher, dir);
		PrettyPrinter.endFunction("Worker", "pushByWorker(firstPusher, pusher, dir)", res ? "true" : "false");
		return res;
	}

	/* (non-Javadoc)
	 * @see skeleton.model.Entity#pushByCrate(skeleton.model.Worker, skeleton.model.Crate, skeleton.model.Direction)
	 */
	@Override
	public boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir) {
		PrettyPrinter.startFunction("Worker", "pushByCrate(firstPusher, pusher, dir)");
		boolean res = step(firstPusher, dir);
		PrettyPrinter.endFunction("Worker", "pushByCrate(firstPusher, pusher, dir)", res ? "true" : "false");
		return res;
	}
	
	/**
	 *The Worker gets one point. 
	 */
	public void gainPoint() {
		PrettyPrinter.startFunction("Worker", "gainPoint()");
		points++;
		PrettyPrinter.endFunction("Worker", "gainPoint()");
	}
	/**
	 * The Worker loses one point.
	 */
	public void losePoint() {
		PrettyPrinter.startFunction("Worker", "losePoint()");
		points--;
		PrettyPrinter.endFunction("Worker", "losePoint()");
	}
	
	/**
	 * Returns with the quantity of the Worker's points.
	 * 
	 * @return points	The Worker's points.
	 */
	public int getPoints() {
		PrettyPrinter.startFunction("Worker", "getPoints()");
		PrettyPrinter.endFunction("Worker", "getPoints()", "points");
		return points;
	}
	
	/**
	 * The worker gets one healthpoint.
	 */
	public void gainHealth() {
		PrettyPrinter.startFunction("Worker", "gainHealth()");
		health++;
		PrettyPrinter.endFunction("Worker", "gainHealth()");
	}
	
	/**
	 * The Worker loses one healthpoint.
	 */
	public void loseHealth() {
		PrettyPrinter.startFunction("Worker", "loseHealth()");
		health--;
		PrettyPrinter.endFunction("Worker", "loseHealth()");
	}
	
	/**
	 * Returns with the quantity of the Worker's healthpoints. 
	 *
	 * @return health	The Worker's healtpoints.
	 */
	public int getHealth() {
		PrettyPrinter.startFunction("Worker", "getHealth()");
		PrettyPrinter.endFunction("Worker", "getHealth()", "health");
		return health;
	}
	
	/**
	 * The Worker respawns at their spawnField.
	 */
	public void reSpawn() {
		PrettyPrinter.startFunction("Worker", "reSpawn()");
		this.setCurField(spawnField);
		spawnField.setEntity(this);
		PrettyPrinter.endFunction("Worker", "reSpawn()");
	}
	
	/**
	 * The Worker dies
	 */
	public void die() {
		throw new RuntimeException("Unimplemented!");
		// TODO Remove worker from game.
	}
	
	/* (non-Javadoc)
	 * @see skeleton.model.IVisitor#visit(skeleton.model.Worker, skeleton.model.IVisitable)
	 */
	@Override
	public boolean visit(Worker firstPusher, IVisitable iv) {
		PrettyPrinter.startFunction("Worker", "visit(firstPusher, iv)");
		boolean ret = iv.visitByWorker(firstPusher, this);
		PrettyPrinter.endFunction("Worker", "visit(firstPusher, iv)", ret ? "true" : "false");
		return ret;
	}
	
}
