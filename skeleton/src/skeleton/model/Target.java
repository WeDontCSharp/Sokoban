package skeleton.model;

public class Target extends Floor {
	
	private Worker whoPushed;

	public Target(Warehouse level, int x, int y) {
		super(level, x, y);
	}
	
	public void unsetEntity() {
		super.unsetEntity();
		if (whoPushed != null) {
			whoPushed.losePoint();
			whoPushed = null;
		}
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		super.acceptEntity(c);
		whoPushed = firstPusher;
		firstPusher.gainPoint();
		return true;
	}

}
