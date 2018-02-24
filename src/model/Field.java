package model;
import java.util.List;
import java.util.Optional;

import gfx.Screen;

public abstract class Field implements IVisitable {
	private Optional<Entity> entityHere;
	private boolean locked;
	private int x;
	private int y;
	
	public Field(int x, int y) {
		this.entityHere = Optional.empty();
		this.locked = false;
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void render(Screen screen);
	public abstract boolean accept(List<Entity> entities, Direction dir);
	
	public Optional<Entity> getEntityHere() {
		return entityHere;
	}
	
	public void setEntityHere(Entity e) {
		entityHere = Optional.of(e);
	}
	
	public void unsetEntity() {
		entityHere = Optional.empty();
	}
	
	public void lock() {
		locked = true;
	}
	
	public void unlock() {
		locked = false;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean visitBy(Worker w, List<Entity> ents) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean visitBy(Crate c, List<Entity> ents) {
		// TODO Auto-generated method stub
		return true;
	}
}
