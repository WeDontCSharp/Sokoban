package skeleton.model;

public class Spawn extends Floor {

	private Worker owner;
	
	public Spawn(Warehouse level, int x, int y, Worker owner) {
		super(level, x, y);
		this.owner = owner;
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		if (w != owner) {
			w.loseHealth();
			return true;
		} else {
			return false;
		}
	}
	
	public Worker getOwner() {
		return owner;
	}

}
