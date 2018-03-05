package skeleton;

public class Crate extends Entity {
		
	public Crate(Warehouse g, Field f) {
		super(g, f);			
	}
	
	public boolean step(Worker firstPusher, Direction dir) {
		Field nextField = super.getCurField().getNeighbourField(dir);
		if (nextField.canVisitBy(firstPusher, this)){
			if (nextField.isEmpty()) {
				super.getCurField().unsetEntity();
				super.setCurField(nextField);
				nextField.acceptEntity(firstPusher, this);
				return true;
			}
			else {
				Entity nextEntity = nextField.getCurEntity().get();
				if (push(firstPusher, nextEntity, dir)) {
					super.getCurField().unsetEntity();
					super.setCurField(nextField);
					nextField.acceptEntity(firstPusher, this);
					return true;
				}
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean push(Worker firstPusher, Entity pushed, Direction dir) {
		return pushed.pushBy(firstPusher, this, dir);
	}

	@Override
	public boolean pushBy(Worker firstPusher, Worker pushed, Direction dir) {
		if (firstPusher == pushed) {
			return false;
		}
		return step(firstPusher, dir);	
	}

	@Override
	public boolean pushBy(Worker firstPusher, Crate pusher, Direction dir) {
		return step(firstPusher, dir);
	}
	
	@Override
	public boolean canVisit(Worker firstPusher, IVisitable iv) {
		return iv.canVisitBy(firstPusher, this);
	}
	
	public void remove() {
		// TODO
	}

	@Override
	public void fallDown(Worker firstPusher) {
		remove();
	}
	
}
