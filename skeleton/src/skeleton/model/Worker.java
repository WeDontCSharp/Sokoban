package skeleton.model;

import skeleton.meta.PrettyPrinter;

/**
 * A class representing a worker.
 */
public class Worker extends Entity {
	/**
	 * The points of the worker.
	 */
	private int points;
	/**
	 * The health of the worker. The worker dies
	 * if their helath reaches 0.
	 */
	private int health;
	/**
	 * The current power of the worker that decreases
	 * when trying to push something.
	 */
	private double power;
	/**
	 * The original power of the worker.
	 */
	private double orgPower;
	/**
	 * The item the worker can change a field's
	 * slipperines with.
	 */
	private PlaceableItem item;
	/**
	 * The field where the worker starts the game
	 * and respawns when losing a life.
	 * @see PlaceableItem
	 */
	private Field spawnField;
	
	/**
	 * Worker's constructor. The generated worker appears ion it's spawner field,
	 * which is specified in the constuctor.
	 * 
	 * @param g 	Warehouse, where the entities are listed and specifies the level.
	 * @param f 	The new worker's spawnField, spawns here immediately.
	 * @param dir 	The direction, where the worker is heading.
	 */
	public Worker(Warehouse g, Field f, Direction dir) {
		super(g, f);
		this.spawnField = f;
		this.orgPower = 2.0;
		this.power = 2.0;
	}
	
	/**
	 * The worker's power decreases by the taken value,
	 * because they're trying to push something.
	 * @param power The amount of power required to push
	 * the entity the worker is trying to.
	 * @return The reduced power of the worker. The negative
	 * power represents that the worker does not have
	 * enough power to push the entity.
	 */
	public double consumePower(double power) {
		return this.power - power;
	}
	
	/**
	 * Places the item the worker currently holds onto
	 * the field the worker is currently staying on.
	 */
	public void placeItem() {
		// TODO: What to do on successfull/failed place? 
		if (item == PlaceableItem.Honey) {
			getCurField().placeSlipFactor(2.0);
		}
		else if (item == PlaceableItem.Oil) {
			getCurField().placeSlipFactor(0.0);
		}
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
		double remPower = firstPusher.consumePower(getCurField().getSlipFactor() * getWeight());
		if (remPower < 0) {
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
		double remPower = firstPusher.consumePower(getCurField().getSlipFactor() * getWeight());
		if (remPower < 0) {
			return false;
		}
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
