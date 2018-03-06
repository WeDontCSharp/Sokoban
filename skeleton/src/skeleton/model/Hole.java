package skeleton.model;

public class Hole extends Floor {
	
	private boolean open = false;
	private Worker whoPushed;
	
	public Hole(Warehouse level, int x, int y) {
		super(level, x, y);
	}
	
	@Override
	public void acceptEntity(Worker firstPusher, Worker w) {
		super.acceptEntity(w);
		if (open) {
			w.fallDown(firstPusher);
		}
	}
	
	@Override
	public void acceptEntity(Worker firstPusher, Crate c) {
		super.acceptEntity(c);
		whoPushed = firstPusher;
		if (open) {
			c.fallDown(firstPusher);
		}
	}
	
	@Override
	public void acceptEntity(Worker firstPusher, LifeCrate lc) {
		super.acceptEntity(lc);
		whoPushed = firstPusher;
		if (open) {
			lc.fallDown(firstPusher);
		}
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public Worker getWhoPushed() {
		return whoPushed;
	}

}
