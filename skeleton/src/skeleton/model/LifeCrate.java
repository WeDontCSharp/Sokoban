package skeleton.model;

import skeleton.meta.PrettyPrinter;

public class LifeCrate extends Crate {
		
	public LifeCrate(Warehouse g, Field f) {
		super(g, f);			
	}
	
	public void remove(Worker firstPusher) {
		super.remove();
		firstPusher.gainHealth();
	}
	
	@Override
	public boolean visit(Worker firstPusher, IVisitable iv) {
		PrettyPrinter.startFunction("LifeCrate", "visit(firstPusher, iv)");
		boolean res = iv.visitByLifeCrate(firstPusher, this);
		PrettyPrinter.endFunction("LifeCrate", "visit(firstPusher, iv)", res ? "true" : "false");
		return res;
	}
	
}
