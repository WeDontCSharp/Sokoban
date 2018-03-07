package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Worker extends Entity {
	private int points;
	private int health;
	
	public Worker(Warehouse g, Field f, Direction dir) {
		super(g, f);			
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
		points++;
	}
	
	public void losePoint() {
		points--;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void gainHealth() {
		health++;
	}
	
	public void loseHealth() {
		health--;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void reSpawn() {
		Field f = getLevel().getField(1, 1);
		this.setCurField(f);
		f.setCurEntity(this);
	}
	
	public void die() {
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
