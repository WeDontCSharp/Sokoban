package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Hole extends Floor {
	
	private Boolean open = null;
	private Worker whoPushed;
	
	public Hole(Warehouse level) {
		super(level);
	}
	
	@Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Hole", "visitByWorker(firstPusher, w)");
		if (isOpen()) {
			w.loseHealth();
		}
		else {
			super.setEntity(w);
		}
		PrettyPrinter.endFunction("Hole", "visitByWorker(firstPusher, w)", "true");
		return true;
	}

	@Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Hole", "visitByCrate(firstPusher, c)");
		whoPushed = firstPusher;
		if (isOpen()) {
			c.remove();
		}
		else {
			super.setEntity(c);
		}
		PrettyPrinter.endFunction("Hole", "visitByCrate(firstPusher, c)", "true");
		return true;
	}
	
	@Override
	public boolean visitByLifeCrate(Worker firstPusher, LifeCrate lc) {
		PrettyPrinter.startFunction("Hole", "visitByLifeCrate(firstPusher, lc)");
		whoPushed = firstPusher;
		if (isOpen()) {
			lc.remove(firstPusher);
		}
		else {
			super.setEntity(lc);
		}
		PrettyPrinter.endFunction("Hole", "visitByLifeCrate(firstPusher, lc)", "true");
		return true;
	}
	
	public boolean isOpen() {
		PrettyPrinter.startFunction("Hole", "isOpen()");
		if (open == null) {
			// It is not decided yet
			open = PrettyPrinter.askQuestion(
					"Is the hole open? [Y - yes, N - no] : ", 
					"YN", 
					new Boolean[] { true, false }
			);
		}
		PrettyPrinter.endFunction("Hole", "isOpen()", open ? "true" : "false");
		return open;
	}

	public void setOpen(boolean open) {
		PrettyPrinter.startFunction("Hole", "setOpen(open)");
		this.open = open;
		PrettyPrinter.endFunction("Hole", "setOpen(open)");
	}
	
	public Worker getWhoPushed() {
		PrettyPrinter.startFunction("Hole", "getWhoPushed()");
		PrettyPrinter.endFunction("Hole", "getWhoPushed()", "whoPushed");
		return whoPushed;
	}

}
