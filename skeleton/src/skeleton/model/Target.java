package skeleton.model;

public class Target extends Floor {
	
	private Worker whoPushed;

	public Target(Warehouse level) {
		super(level);
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
		super.setEntity(c);
		whoPushed = firstPusher;
		firstPusher.gainPoint();
		return true;
	}

}
