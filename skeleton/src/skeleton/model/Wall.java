package skeleton.model;

public class Wall extends Field  {

	public Wall(Warehouse level, int x, int y) {
		super(level, x, y);
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		w.loseHealth();
		return true;
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		return false;
	}

}

