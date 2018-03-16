package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Spawn extends Floor {

	private Worker owner;
	
	public Spawn(Warehouse level, Worker owner) {
		super(level);
		this.owner = owner;
	}

	@Override
	public boolean visitByWorker(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Floor", "visitByWorker(firstPusher, w)");
		if (owner == null) {
			Boolean ans = PrettyPrinter.askQuestion("Is the pushed worker the owner of this spawn? [Y - yes, N - no] : ", 
					"YN", new Boolean[] { true, false
			});
			if (ans.booleanValue()) {
				this.owner = w;
			}
		}
		if (w != firstPusher && w != owner) {
			w.loseHealth();
			PrettyPrinter.endFunction("Floor", "visitByWorker(firstPusher, w)", "true");
			return true;
		}
		else if (w == owner) {
			PrettyPrinter.endFunction("Floor", "visitByWorker(firstPusher, w)", "true");
			return true;
		}
		else {
			PrettyPrinter.endFunction("Floor", "visitByWorker(firstPusher, w)", "false");
			return false;
		}
	}

	@Override
	public boolean visitByCrate(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Floor", "visitByCrate(firstPusher, c)");
		PrettyPrinter.endFunction("Floor", "visitByCrate(firstPusher, c)", "false");
		return false;
	}
	
	public Worker getOwner() {
		PrettyPrinter.startFunction("Spawn", "getOwner()");
		PrettyPrinter.endFunction("Spawn", "getOwner()", "owner");
		return owner;
	}
	
	@Override
	public boolean isEmpty() {
		PrettyPrinter.startFunction("Spawn", "isEmpty()");
		PrettyPrinter.endFunction("Spawn", "isEmpty()", "true");
		return true;
	}

}
