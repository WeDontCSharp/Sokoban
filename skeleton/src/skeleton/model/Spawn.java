package skeleton.model;

public class Spawn extends Floor {

	private Worker owner;
	
	public Spawn(Warehouse level, int x, int y, Worker owner) {
		super(level, x, y);
		this.owner = owner;
	}

	@Override
	public boolean canVisitBy(Worker firstPusher, Worker w) {
		return w == owner || w != firstPusher;
	}

	@Override
	public boolean canVisitBy(Worker firstPusher, Crate c) {
		return false;
	}

	@Override
	public void acceptEntity(Worker firstPusher, Worker w) {
		if (w != owner) {
			w.loseHealth();
		}
	}
	
	public Worker getOwner() {
		return owner;
	}

}
