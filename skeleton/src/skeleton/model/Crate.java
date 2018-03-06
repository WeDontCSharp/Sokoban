package skeleton.model;

public class Crate extends Entity {
		
	public Crate(Warehouse g, Field f) {
		super(g, f);			
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
		// TODO Remove crate from game.
	}

	@Override
	public void visit(Worker firstPusher, IVisitable iv) {
		iv.visitBy(firstPusher, this);
	}
	
}
