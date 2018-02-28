package model;
import java.util.List;
import java.util.Optional;

import gfx.Screen;

public abstract class Field {
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
	public abstract boolean canStepHere(Worker firstPusher, Worker w);
	public abstract boolean canStepHere(Worker firstPusher, Crate c);
	
	public void setEntityHere(Worker firstPusher, Worker w) {
		setEntityHere(w);
	}

	public void setEntityHere(Worker firstPusher, Crate c) {
		setEntityHere(c);
	}
	
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
}
