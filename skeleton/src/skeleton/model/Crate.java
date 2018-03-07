package skeleton.model;

public class Crate extends Entity {
		
	public Crate(Warehouse g, Field f) {
		super(g, f);			
	}

	@Override
	public boolean push(Worker firstPusher, Entity pushed, Direction dir) {
		return pushed.pushByCrate(firstPusher, this, dir);
	}

	@Override
	public boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir) {
		return step(firstPusher, dir);	
	}

	@Override
	public boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir) {
		return step(firstPusher, dir);
	}
	
	public void remove() {
		// TODO Remove crate from game.
	}

	@Override
	public boolean visit(Worker firstPusher, IVisitable iv) {
		return iv.visitByCrate(firstPusher, this);
	}
	
}
