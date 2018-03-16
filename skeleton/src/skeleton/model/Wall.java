package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Wall extends Field  {

	public Wall(Warehouse level) {
		super(level);
	}

	// XXX: Still acceptEntity? Shouldn't it be acceptWorker and acceptCrate?
	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Wall", "acceptEntity(firstPusher, w)");
		if (firstPusher != w) {
			w.loseHealth();
			PrettyPrinter.endFunction("Wall", "acceptEntity(firstPusher, w)", "true");
			return true;
		}
		else {
			PrettyPrinter.endFunction("Wall", "acceptEntity(firstPusher, w)", "false");
			return false;
		}
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Wall", "acceptEntity(firstPusher, c)");
		PrettyPrinter.endFunction("Wall", "acceptEntity(firstPusher, c)", "false");
		return false;
	}

}

