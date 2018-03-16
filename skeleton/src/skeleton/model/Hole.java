package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Hole extends Floor {
	
	private Boolean open = null;
	private Worker whoPushed;
	
	public Hole(Warehouse level) {
		super(level);
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Hole", "acceptEntity(firstPusher, w)");
		super.setEntity(w);
		if (isOpen()) {
			w.loseHealth();
		}
		PrettyPrinter.endFunction("Hole", "acceptEntity(firstPusher, w)", "true");
		return true;
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Hole", "acceptEntity(firstPusher, c)");
		super.setEntity(c);
		whoPushed = firstPusher;
		if (isOpen()) {
			c.remove();
		}
		PrettyPrinter.endFunction("Hole", "acceptEntity(firstPusher, c)", "true");
		return true;
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, LifeCrate lc) {
		PrettyPrinter.startFunction("Hole", "acceptEntity(firstPusher, lc)");
		super.setEntity(lc);
		whoPushed = firstPusher;
		if (isOpen()) {
			lc.remove(firstPusher);
		}
		PrettyPrinter.endFunction("Hole", "acceptEntity(firstPusher, lc)", "true");
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
