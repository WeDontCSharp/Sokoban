package skeleton.model;

public class Hole extends Floor {
	
	private boolean open = false;
	private Worker whoPushed;
	
	public Hole(Warehouse level) {
		super(level);
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Worker w) {
		super.setEntity(w);
		if (open) {
			w.loseHealth();
		}
		return true;
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, Crate c) {
		super.setEntity(c);
		whoPushed = firstPusher;
		if (open) {
			c.remove();
		}
		return true;
	}
	
	@Override
	public boolean acceptEntity(Worker firstPusher, LifeCrate lc) {
		super.setEntity(lc);
		whoPushed = firstPusher;
		if (open) {
			lc.remove(firstPusher);
		}
		return true;
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
