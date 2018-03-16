package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Hole extends Floor {
	
	private boolean open = false;
	private Worker whoPushed;
	
	public Hole(Warehouse level) {
		super(level);
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Hole", "acceptEntity(firstPusher, w)");
		super.setEntity(w);
		if (open) {
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
		if (open) {
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
		if (open) {
			lc.remove(firstPusher);
		}
		PrettyPrinter.endFunction("Hole", "acceptEntity(firstPusher, lc)", "true");
		return true;
	}

	public boolean isOpen() {
		PrettyPrinter.startFunction("Hole", "isOpen()");
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
