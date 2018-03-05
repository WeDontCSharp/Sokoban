package skeleton;

public class Wall extends Field  {

	public Wall(Warehouse level, int x, int y) {
		super(level, x, y);
	}

	@Override
	public boolean canVisitBy(Worker firstPusher, Worker w) {
		return w != firstPusher;
	}

	@Override
	public boolean canVisitBy(Worker firstPusher, Crate c) {
		return false;
	}

	@Override
	public void acceptEntity(Worker firstPusher, Worker w) {
		w.loseHealth();
	}

	@Override
	public void acceptEntity(Worker firstPusher, Crate c) {
		// Chillin' & savin' its class from being abstract.
	}

}

