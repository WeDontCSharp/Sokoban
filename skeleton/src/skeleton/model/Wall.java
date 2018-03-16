package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Wall extends Field  {

	public Wall(Warehouse level) {
		super(level);
	}
	
	@Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Wall", "visitByWorker(firstPusher, w)");
		if (firstPusher != w) {
			w.loseHealth();
			PrettyPrinter.endFunction("Wall", "visitByWorker(firstPusher, w)", "true");
			return true;
		}
		else {
			PrettyPrinter.endFunction("Wall", "visitByWorker(firstPusher, w)", "false");
			return false;
		}
	}

	@Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Wall", "visitByCrate(firstPusher, c)");
		PrettyPrinter.endFunction("Wall", "visitByCrate(firstPusher, c)", "false");
		return false;
	}

}

