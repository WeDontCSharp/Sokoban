package skeleton.model;

import java.util.Optional;

/**
 * An abstract class representing a field.
 */
public abstract class Field implements IVisitable {
	/**
	 * The warehouse the field is in.
	 */
	private Warehouse level;
	/**
	 * The neighbouring fields of the field.
	 */
	private Field[] neighbours;
	/**
	 * The entity that is currently on the field.
	 */
	private Optional<Entity> curEntity;
	
	private boolean locked;
	
	
	/**
	 * Represents the slipperiness of a field. If it's above
	 * 1.0, the field is sticky (it contains honey), if it's
	 * below 1.0, the field is slippery (it contains oil). The
	 * slipFactor tends to 1.0.
	 * The larger the slipFactor on a field is, the more power 
	 * is required to push an entity from it.
	 */
	private double slipFactor;

	/**
	 * Creates a field.
	 * @param level The warehouse to the create the field in.
	 */
	public Field(Warehouse level) {
		this.level = level;
		this.neighbours = new Field[Direction.values().length];
		this.slipFactor = 1.0;
		this.locked = false;
		this.curEntity = Optional.empty();
	}

	/**
	 * Sets the slipperiness of a field.
	 * @param slipFactor The new value that replaces the current factor.
	 * @return True, if the change was successfull, false otherwise.
	 */
	public boolean placeSlipFactor(double slipFactor) {
		return false;
	}
	
	/**
	 * @return The slipperiness of the field.
	 */
	public double getSlipFactor() {
		return slipFactor;
	}

	/**
	 * @param slipFactor The slipperiness of the field.
	 */
	public void setSlipFactor(double slipFactor) {
		this.slipFactor = slipFactor;
	}

	@Override
	public boolean visitByLifeCrate(Worker firstPusher, LifeCrate lc) {
		return visitByCrate(firstPusher, lc);
	}
	
	/**
	 * Gets an entity from the field.
	 * @return The entity on the field.
	 */
	public Optional<Entity> getEntity() {
		return curEntity;
	}
	
	/**
	 * Puts an entity on the field.
	 * @param e The entity to be set.
	 */
	public void setEntity(Entity e) {
		curEntity = Optional.of(e);
	}
	
	/**
	 * Removes the entity from the field.
	 */
	public void unsetEntity() {
		curEntity = Optional.empty();
	}
	
	/**
	 * Tells whether the there's an entity on the field or it's empty.
	 * @return True if the field is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return !this.curEntity.isPresent();
		
	}
	
	/**
	 * Sets the neighbor in a given direction.
	 * 
	 * @param dir The direction the neighbor is in.
	 * @param f The neighbor field.
	 */
	public void setNeighbourField(Direction dir, Field f) {
		neighbours[dir.ordinal()] = f;
	}
	
	/**
	 * Gets a neighbouring field.
	 * @param dir The direction to the get the neighbouring field in.
	 * @return The neighbouring field.
	 */
	public Field getNeighbourField(Direction dir) {
		return neighbours[dir.ordinal()];
	}
	
	/**
	 * Gets the warehouse of the field.
	 * @return The warehouse.
	 */
	public Warehouse getLevel() {
		return level;
	}
	
	public void lock() {
		this.locked = true;
	}
	
	public void unlock() {
		this.locked = false;
	}
	
	public boolean isLocked() {
		return this.locked;
	}
}
