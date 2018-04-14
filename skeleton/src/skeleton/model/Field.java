package skeleton.model;

import java.util.Optional;

import skeleton.meta.PrettyPrinter;

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
		PrettyPrinter.startFunction("Field", "visitByLifeCrate(firstPusher, lc)");
		boolean res = visitByCrate(firstPusher, lc);
		PrettyPrinter.endFunction("Field", "visitByLifeCrate(firstPusher, lc)", res ? "true" : "false");
		return res;
	}
	
	/**
	 * Gets an entity from the field.
	 * @return The entity on the field.
	 */
	public Optional<Entity> getEntity() {
		PrettyPrinter.startFunction("Field", "getEntity()");
		PrettyPrinter.endFunction("Field", "getEntity()", "curEntity");
		return curEntity;
	}
	
	/**
	 * Puts an entity on the field.
	 * @param e The entity to be set.
	 */
	public void setEntity(Entity e) {
		PrettyPrinter.startFunction("Field", "setEntity(e)");
		curEntity = Optional.of(e);
		PrettyPrinter.endFunction("Field", "setEntity(e)");
	}
	
	/**
	 * Removes the entity from the field.
	 */
	public void unsetEntity() {
		PrettyPrinter.startFunction("Field", "unsetEntity()");
		curEntity = Optional.empty();
		PrettyPrinter.endFunction("Field", "unsetEntity()");
	}
	
	/**
	 * Tells whether the there's an entity on the field or it's empty.
	 * @return True if the field is empty, false otherwise.
	 */
	public boolean isEmpty() {
		PrettyPrinter.startFunction("Field", "isEmpty()");
		if (this.curEntity == null) {
			// We haven't handled this field! We need to ask the user what he wants here
			Boolean answ = PrettyPrinter.askQuestion(
					"Is an entity already on this field? [Y - yes, N - no] : ", 
					"YN", new Boolean[] { true, false });
			if (answ.booleanValue()) {
				// XXX: More?
				Entity e = PrettyPrinter.askQuestion(
					"What kind of entity? [W - Worker, C - Crate, L - LifeCrate] : ", 
					"WCL", new Entity[] { 
							new Worker(getLevel(), this, Direction.Right), 
							new Crate(getLevel(), this),
							new LifeCrate(getLevel(), this)
				});
				this.curEntity = Optional.of(e);
			}
			else {
				this.curEntity = Optional.empty();
			}
		}
		PrettyPrinter.endFunction("Field", "isEmpty()", this.curEntity.isPresent() ? "false" : "true");
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
		PrettyPrinter.startFunction("Field", "getNeighbourField(dir)");
		if (neighbours[dir.ordinal()] == null) {
			// We haven't handled this field! We need to ask the user what he wants here
			// XXX: more types?
			Field n = PrettyPrinter.askQuestion(
					"What kind of field is the neighbor? [F - floor, W - wall, H - hole, T - target, S - switch, P - spawn] : ",
					"FWHTSP", new Field[] { 
							new Floor(this.level),
							new Wall(this.level),
							new Hole(this.level),
							new Target(this.level),
							new Switch(this.level),
							new Spawn(this.level, null)
			});
			
			neighbours[dir.ordinal()] = n;
		}
		PrettyPrinter.endFunction("Field", "getNeighbourField(dir)", "neighbor");
		return neighbours[dir.ordinal()];
	}
	
	/**
	 * Gets the warehouse of the field.
	 * @return The warehouse.
	 */
	public Warehouse getLevel() {
		PrettyPrinter.startFunction("Field", "getLevel()");
		PrettyPrinter.endFunction("Field", "getLevel()", "level");
		return level;
	}
}
