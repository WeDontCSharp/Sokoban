package skeleton.model;

public class Floor extends Field {

	public Floor(Warehouse level, int x, int y) {
		super(level, x, y);
	}

	@Override
	public boolean canVisitBy(Worker firstPusher, Worker w) {
		return true;
	}

	@Override
	public boolean canVisitBy(Worker firstPusher, Crate c) {
		return true;
	}

	@Override
	public void acceptEntity(Worker firstPusher, Worker w) {
		super.acceptEntity(w);
	}

	@Override
	public void acceptEntity(Worker firstPusher, Crate c) {
		super.acceptEntity(c);
	}

}
