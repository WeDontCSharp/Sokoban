package skeleton.model;

public abstract class Entity implements IVisitor {
	protected Warehouse level;
	private int x;
	private int y;
	private Field curField;
	
	public Entity(Warehouse level, Field f) {
		this.level = level;
		this.x = f.getX();
		this.y = f.getY();
		this.curField = f;
		f.setCurEntity(this);
	}
	
	public abstract boolean push(Worker firstPusher, Entity pushed, Direction dir);
	public abstract boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir);
	public abstract boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir);
	
	public boolean step(Worker firstPusher, Direction dir) {
		System.out.println("> [:Entity].step(firstPusher, dir)");
		Field nextField = getCurField().getNeighbourField(dir);
		if (nextField.isEmpty()) {
			if (visit(firstPusher, nextField)) {
				getCurField().unsetEntity();
				setCurField(nextField);
				System.out.println("< TRUE [:Entity].step(firstPusher, dir)");
				return true;
			}
		} else {
			Entity nextEntity = nextField.getCurEntity().get();
			if (push(firstPusher, nextEntity, dir)) {
				getCurField().unsetEntity();
				setCurField(nextField);
				System.out.println("< TRUE [:Entity].step(firstPusher, dir)");
				return true;
			}
		}
		System.out.println("< FALSE  [:Entity].step(firstPusher, dir)");
		return false;
	}
		
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCurField(Field f) {
		this.curField = f;
	}
	
	public Field getCurField() {
		return this.curField;
	}
	
	public Warehouse getLevel() {
		return level;
	}
	
}
