package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Floor extends Field {

	public Floor(Warehouse level) {
		super(level);
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Floor", "acceptEntity(firstPusher, w)");
		super.acceptEntity(w);
		PrettyPrinter.endFunction("Floor", "acceptEntity(firstPusher, w)", "true");
		return true;
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		super.acceptEntity(c);
		return true;
	}

}
