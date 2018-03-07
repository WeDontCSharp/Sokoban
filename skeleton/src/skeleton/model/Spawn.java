package skeleton.model;

public class Spawn extends Floor {

	private Worker owner;
	
	public Spawn(Warehouse level, Worker owner) {
		super(level);
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
	
	public boolean isEmpty() {
		return true;
	}

}
