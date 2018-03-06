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
		step(this, dir);
	}
	
	public boolean step(Worker firstPusher, Direction dir) {
		Field nextField = super.getCurField().getNeighbourField(dir);
		if (nextField.canVisitBy(firstPusher, this)){
			if (nextField.isEmpty()) {
				super.getCurField().unsetEntity();
				super.setCurField(nextField);
				nextField.acceptEntity(firstPusher, this);
				return true;
			}
			else {
				Entity nextEntity = nextField.getCurEntity().get();
				if (push(firstPusher, nextEntity, dir)) {
					if (this == firstPusher) {
						// TODO
					}
					else {
						// TODO
					}
					super.getCurField().unsetEntity();
					super.setCurField(nextField);
					nextField.acceptEntity(firstPusher, this);
					return true;
				}
				return false;
			}
		}
		return false;
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
		// TODO
	}

	@Override
	public void fallDown(Worker firstPusher) {
		loseHealth();
	}
	
}
