package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Spawn extends Floor {

	private Worker owner;
	
	public Spawn(Warehouse level, Worker owner) {
		super(level);
		this.owner = owner;
	}

	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		PrettyPrinter.startFunction("Spawn", "acceptEntity(firstPusher, w)");
		if (owner == null) {
			Boolean ans = PrettyPrinter.askQuestion("Is the worker the owner of this spawn? [Y - yes, N - no] : ", 
					"YN", new Boolean[] { true, false
			});
			if (ans.booleanValue()) {
				this.owner = w;
			}
		}
		if (w != owner) {
			w.loseHealth();
			PrettyPrinter.endFunction("Spawn", "acceptEntity(firstPusher, w)", "true");
			return true;
		} else {
			// XXX: It should be true I think!
			PrettyPrinter.endFunction("Spawn", "acceptEntity(firstPusher, w)", "false");
			return false;
		}
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
