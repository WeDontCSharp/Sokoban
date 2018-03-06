package skeleton.model;

public class LifeCrate extends Crate {
		
	public LifeCrate(Warehouse g, Field f) {
		super(g, f);			
	}
	
	public void remove(Worker firstPusher) {
		super.remove();
		firstPusher.gainHealth();
	}
	
	@Override
	public void visit(Worker firstPusher, IVisitable iv) {
		iv.visitBy(firstPusher, this);
	}
	
}
