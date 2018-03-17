package skeleton.model;

import skeleton.meta.PrettyPrinter;

/**
 * An abstract class representing an entity.
 */
public abstract class Entity implements IVisitor {
	/**
	 * The warehouse the entity is in.
	 */
	protected Warehouse level;
	/**
	 * The field where the entity is currently standing.
	 */
	private Field curField;
	
	/**
	 * The entity's constructor.
	 * 
	 * @param g Warehouse, where the entities are listed and specifies the level.
	 * @param f	The field, where the entity appears.
	 */
	public Entity(Warehouse level, Field f) {
		this.level = level;
		this.curField = f;
		f.setEntity(this);
	}
	/**
	 * An abstract method, where the entity pushes an other entity directly, or in a chain.
	 * 
	 * @param firstPusher 	The worker, who makes the push.
	 * @param pushed 		An entity, which is pushed.
	 * @param dir			The direction of the push mechanism.
	 * @return true, if the push succeed, so the entity moved, false otherwise.
	 */
	public abstract boolean push(Worker firstPusher, Entity pushed, Direction dir);
	
	/**
	 * An abstract method, where the entity pushed by a worker directly.
	 * 
	 * @param firstPusher 	The worker, who makes the push.
	 * @param pusher 		The worker, who pushed the entity.
	 * @param dir			The direction of the push mechanism.
	 * @return true, if the push succeed, so the entity moved, false otherwise.
	 */
	public abstract boolean pushByWorker(Worker firstPusher, Worker pusher, Direction dir);
	
	/**
	 * An abstract method, where the entity pushed by a create directly.
	 * 
	 * @param firstPusher 	The worker, who makes the push.
	 * @param pusher 		The create, who pushed the entity.
	 * @param dir			The direction of the push mechanism.
	 * @return true, if the push succeed, so the entity moved, false otherwise.
	 */
	public abstract boolean pushByCrate(Worker firstPusher, Crate pusher, Direction dir);
	
	/**
	 * The entity steps in a direction. If the next field is empty, the entity steps there,
	 * if occupied, it tries to push it's occupier, if succeed, the steps there, otherwise
	 * stays on thier original field
	 * 
	 * @param firstPusher	The worker, who makes the push.
	 * @param dir			The direction of the step.
	 * @return true, if the step succeed, so the entity moved, false otherwise.
	 */
	public boolean step(Worker firstPusher, Direction dir) {
		PrettyPrinter.startFunction("Entity", "step(firstPusher, dir)");
		Field neighbour = curField.getNeighbourField(dir);
		if (neighbour.isEmpty()) {											//If the next fiel is empty
			if (visit(firstPusher, neighbour)) {
				curField.unsetEntity();
				setCurField(neighbour);
				PrettyPrinter.endFunction("Entity", "step(firstPusher, dir)", "true");
				return true;
			}
		} else {												//If the next fiel is occupied, tries to push
			Entity nextEntity = neighbour.getEntity().get();
			if (push(firstPusher, nextEntity, dir)) {
				if (visit(firstPusher, neighbour)) {
					curField.unsetEntity();
					setCurField(neighbour);
					PrettyPrinter.endFunction("Entity", "step(firstPusher, dir)", "true");
					return true;
				}
			}
		}
		PrettyPrinter.endFunction("Entity", "step(firstPusher, dir)", "false");
		return false;
	}
	
	/**
	 * Sets the entity's curretnField to a new field.
	 * 
	 * @param f	the new field
	 */
	public void setCurField(Field f) {
		PrettyPrinter.startFunction("Entity", "setCurField(f)");
		this.curField = f;
		PrettyPrinter.endFunction("Entity", "setCurField(f)");
	}
	
	/**
	 * Returns with the entity's currentField.
	 * 
	 * @return the entity's currentField
	 */
	public Field getCurField() {
		PrettyPrinter.startFunction("Entity", "getCurField()");
		PrettyPrinter.endFunction("Entity", "getCurField()", "curField");
		return this.curField;
	}
	
	/**
	 * Returns with the entity's Warehouse, which specifies the level.
	 * 
	 * @return the Warehouse, which contains the entity.
	 */
	public Warehouse getLevel() {
		PrettyPrinter.startFunction("Entity", "getLevel()");
		PrettyPrinter.endFunction("Entity", "getLevel()", "level");
		return level;
	}
	
}
