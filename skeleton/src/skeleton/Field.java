package skeleton;

import java.util.Optional;

public abstract class Field implements IVisitable {
	private Warehouse level;
	private Field[] neighbours;
	private Optional<Entity> curEntity;
	private int x;
	private int y;
	
	public Field(Warehouse level, int x, int y) {
		this.level = level;
		this.neighbours = new Field[Direction.values().length];
		this.curEntity = Optional.empty();
		this.x = x;
		this.y = y;
	}
	
	public void acceptEntity(Entity e) {
		setCurEntity(e);
	}
	
	public abstract void acceptEntity(Worker firstPusher, Worker w);
	public abstract void acceptEntity(Worker firstPusher, Crate c);
	
	public void acceptEntity(Worker firstPusher, LifeCrate lc) {
		acceptEntity(firstPusher, (Crate)lc);
	}
	
	public Optional<Entity> getCurEntity() {
		return curEntity;
	}
	
	public void setCurEntity(Entity e) {
		curEntity = Optional.of(e);
	}
	
	public void unsetEntity() {
		curEntity = Optional.empty();
	}
	
	public boolean isEmpty() {
		return curEntity.isPresent();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setNeighbourField(Direction dir, Field f) {
		neighbours[dir.ordinal()] = f;
	}
	
	public Field getNeighbourField(Direction dir) {
		return neighbours[dir.ordinal()];
	}
	
	public Warehouse getLevel() {
		return level;
	}
}
