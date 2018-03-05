package skeleton;

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
	public abstract boolean pushBy(Worker firstPusher, Worker pusher, Direction dir);
	public abstract boolean pushBy(Worker firstPusher, Crate pusher, Direction dir);
	
	public boolean step(Worker firstPusher, Direction dir) {
		// TODO
		return false;
	}
	
	public abstract void fallDown(Worker firstPusher);
	
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
