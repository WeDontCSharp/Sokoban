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
	public abstract boolean pushBy(Worker firstPusher, Worker pusher, Direction dir);
	public abstract boolean pushBy(Worker firstPusher, Crate pusher, Direction dir);
	
	public boolean step(Worker firstPusher, Direction dir) {
		Field nextField = getCurField().getNeighbourField(dir);
		if (canVisit(firstPusher, nextField)){
			// TODO Ask the user whether the field is empty or not.
			if (nextField.isEmpty()) {
				getCurField().unsetEntity();
				setCurField(nextField);
				visit(firstPusher, nextField);
				return true;
			}
			else {
				// TODO Ask the user to select an entity type to the field, because the field is chosen not to be empty.
				Entity nextEntity = nextField.getCurEntity().get();
				if (push(firstPusher, nextEntity, dir)) {
					getCurField().unsetEntity();
					setCurField(nextField);
					visit(firstPusher, nextField);
					return true;
				}
				return false;
			}
		}
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
