package skeleton.model;

public class Floor extends Field {

	public Floor(Warehouse level, int x, int y) {
		super(level, x, y);
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		super.acceptEntity(w);
		return true;
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		super.acceptEntity(c);
		return true;
	}

}
