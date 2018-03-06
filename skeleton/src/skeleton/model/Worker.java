package skeleton.model;

public class Worker extends Entity {
	
	private Direction direction;
	private int points;
	private int health;
	
	public Worker(Warehouse g, Field f, Direction dir) {
		super(g, f);			
		this.direction = dir;
	}
	
	public void move(Direction dir) {
		// TODO Ask the user to select a field type, then create that field and step onto it.
		// Step returns whether the worker could step or not. This should be checked and put on the screen somehow.
		// Maybe this could be main starting point of the shown sequences, as the result can be viewed here.
		step(this, dir); 
	}

	@Override
	public boolean push(Worker firstPusher, Entity pushed, Direction dir) {
		return pushed.pushBy(firstPusher, this, dir);
	}

	@Override
	public boolean pushBy(Worker firstPusher, Worker pushed, Direction dir) {
		if (firstPusher == pushed) {
			return false;
		}
		return step(firstPusher, dir);	
	}

	@Override
	public boolean pushBy(Worker firstPusher, Crate pusher, Direction dir) {
		return step(firstPusher, dir);
	}
	
	@Override
	public boolean canVisit(Worker firstPusher, IVisitable iv) {
		return iv.canVisitBy(firstPusher, this);
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
	public void visit(Worker firstPusher, IVisitable iv) {
		iv.visitBy(firstPusher, this);
	}
	
}
