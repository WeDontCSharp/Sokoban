package fields;
import java.util.Optional;

import entities.Crate;
import entities.Entity;
import entities.Worker;
import gfx.Bitmap;
import model.Direction;
import model.Grid;
import model.IRenderable;

public abstract class Field implements IRenderable {
	private Grid level;
	private Field[] neighbors;
	private Optional<Entity> entityHere;
	private boolean locked;
	private int x;
	private int y;
	
	public Field(Grid level, int x, int y) {
		this.level = level;
		this.neighbors = new Field[Direction.values().length];
		this.entityHere = Optional.empty();
		this.locked = false;
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void render(Bitmap bmp, int xoff, int yoff);
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
	
	public void setNeighbor(Direction dir, Field f) {
		neighbors[dir.ordinal()] = f;
	}
	
	public Field getNeighbor(Direction dir) {
		return neighbors[dir.ordinal()];
	}
	
	public Grid getLevel() {
		return level;
	}
}
