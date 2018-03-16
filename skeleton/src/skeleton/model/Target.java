package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class Target extends Floor {
	private Worker whoPushed;

	public Target(Warehouse level) {
		super(level);
	}
	
	@Override
	public void unsetEntity() {
		PrettyPrinter.startFunction("Target", "unsetEntity()");
		super.unsetEntity();
		if (whoPushed != null) {
			whoPushed.losePoint();
			whoPushed = null;
		}
		PrettyPrinter.endFunction("Target", "unsetEntity()");
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		PrettyPrinter.startFunction("Target", "acceptEntity(firstPusher, c)");
		super.setEntity(c);
		whoPushed = firstPusher;
		firstPusher.gainPoint();
		PrettyPrinter.endFunction("Target", "acceptEntity(firstPusher, c)", "true");
		return true;
	}

}
