package skeleton.model;

public class Wall extends Field  {

	public Wall(Warehouse level) {
		super(level);
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		if (firstPusher != w) {
			w.loseHealth();
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		return false;
	}

}

