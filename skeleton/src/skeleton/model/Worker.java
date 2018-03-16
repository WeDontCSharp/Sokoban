package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Worker extends Entity {
	private int points;
	private int health;
	private Field spawnField;
	
	public Worker(Warehouse g, Field f, Direction dir) {
		super(g, f);
		this.spawnField = f;
	}
	
	public void move(Direction dir) {
		PrettyPrinter.startFunction("Worker", "move(dir)");
		step(this, dir);
		PrettyPrinter.endFunction("Worker", "move(dir)");
	}

	@Override
	public boolean push(Worker firstPusher, Entity pushed, Direction dir) {
		PrettyPrinter.startFunction("Worker", "push(firstPusher, pushed, dir)");
		boolean res = pushed.pushByWorker(firstPusher, this, dir);
		PrettyPrinter.endFunction("Worker", "push(firstPusher, pushed, dir)", res ? "true" : "false");
		return res;
	}

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

	@Override
	public boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir) {
		PrettyPrinter.startFunction("Worker", "pushByCrate(firstPusher, pusher, dir)");
		boolean res = step(firstPusher, dir);
		PrettyPrinter.endFunction("Worker", "pushByCrate(firstPusher, pusher, dir)", res ? "true" : "false");
		return res;
	}
	
	public void gainPoint() {
		PrettyPrinter.startFunction("Worker", "gainPoint()");
		points++;
		PrettyPrinter.endFunction("Worker", "gainPoint()");
	}
	
	public void losePoint() {
		PrettyPrinter.startFunction("Worker", "losePoint()");
		points--;
		PrettyPrinter.endFunction("Worker", "losePoint()");
	}
	
	public int getPoints() {
		PrettyPrinter.startFunction("Worker", "getPoints()");
		PrettyPrinter.endFunction("Worker", "getPoints()", "points");
		return points;
	}
	
	public void gainHealth() {
		PrettyPrinter.startFunction("Worker", "gainHealth()");
		health++;
		PrettyPrinter.endFunction("Worker", "losePoint()");
	}
	
	public void loseHealth() {
		PrettyPrinter.startFunction("Worker", "loseHealth()");
		health--;
		PrettyPrinter.endFunction("Worker", "loseHealth()");
	}
	
	public int getHealth() {
		PrettyPrinter.startFunction("Worker", "getHealth()");
		PrettyPrinter.endFunction("Worker", "getHealth()", "health");
		return health;
	}
	
	public void reSpawn() {
		PrettyPrinter.startFunction("Worker", "reSpawn()");
		this.setCurField(spawnField);
		spawnField.setEntity(this);
		PrettyPrinter.endFunction("Worker", "reSpawn()");
	}
	
	public void die() {
		throw new RuntimeException("Unimplemented!");
		// TODO Remove worker from game.
	}
	
	@Override
	public boolean visit(Worker firstPusher, IVisitable iv) {
		PrettyPrinter.startFunction("Worker", "visit(firstPusher, iv)");
		boolean ret = iv.visitByWorker(firstPusher, this);
		PrettyPrinter.endFunction("Worker", "visit(firstPusher, iv)", ret ? "true" : "false");
		return ret;
	}
	
}
