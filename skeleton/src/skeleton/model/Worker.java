package skeleton.model;

public class Worker extends Entity {
	private int points;
	private int health;
	
	public Worker(Warehouse g, Field f, Direction dir) {
		super(g, f);			
	}
	
	public void move(Direction dir) {
		step(this, dir);
	}

	@Override
	public boolean push(Worker firstPusher, Entity pushed, Direction dir) {
		return pushed.pushByWorker(firstPusher, this, dir);
	}

	@Override
	public boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir) {
		if (firstPusher == pusher) {
			return false;
		}
		return step(firstPusher, dir);	
	}

	@Override
	public boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir) {
		return step(firstPusher, dir);
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
		this.setPos(f.getX(), f.getY());
		this.setCurField(f);
		f.setCurEntity(this);
	}
	
	public void die() {
		// TODO Remove worker from game.
	}
	
	@Override
	public boolean visit(Worker firstPusher, IVisitable iv) {
		return iv.visitByWorker(firstPusher, this);
	}
	
}
