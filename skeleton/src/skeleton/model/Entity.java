package skeleton.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
	 * The weight of an entity. The heavier an entity is,
	 * the more power is required to push it.
	 */
	private double weight;
	
	private LinkedList<Process> processes;
	
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
		this.weight = 1.0;
		this.processes = new LinkedList<Process>();
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
	 * stays on their original field
	 * 
	 * @param firstPusher	The worker, who makes the push.
	 * @param dir			The direction of the step.
	 * @return true, if the step succeed, so the entity moved, false otherwise.
	 */
	public boolean step(Worker firstPusher, Direction dir) {
		Field neighbour = curField.getNeighbourField(dir);
		if (neighbour.isLocked()) {
			return false;
		}
		if (neighbour.isEmpty()) {											//If the next field is empty
			if (visit(firstPusher, neighbour)) {
				this.pushProcess(new StepProcess(this, curField, neighbour));
				return true;
			}
		} else {												//If the next field is occupied, tries to push
			Entity nextEntity = neighbour.getEntity().get();
			if (push(firstPusher, nextEntity, dir)) {
				if (visit(firstPusher, neighbour)) {
					this.pushProcess(new StepProcess(this, curField, neighbour));
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @return The weight of the entity.
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * @param weight The weight of the entity.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/**
	 * Sets the entity's curretnField to a new field.
	 * 
	 * @param f	the new field
	 */
	public void setCurField(Field f) {
		this.curField = f;
	}
	
	/**
	 * Returns with the entity's currentField.
	 * 
	 * @return the entity's currentField
	 */
	public Field getCurField() {
		return this.curField;
	}
	
	/**
	 * Returns with the entity's Warehouse, which specifies the level.
	 * 
	 * @return the Warehouse, which contains the entity.
	 */
	public Warehouse getLevel() {
		return level;
	}
	
	public void update() {
		Process p = this.getCurrentProcess();
		if (p == null) {
			return;
		}
		p.update();
	}
	
	public void pushProcess(Process proc) {
		this.processes.addLast(proc);
		proc.start();
	}
	
	public Process getCurrentProcess() {
		if (processes.isEmpty()) {
			return null;
		}
		while (processes.peekFirst().isOver()) {
			processes.removeFirst().end();
			if (processes.isEmpty()) {
				return null;
			}
		}
		return processes.peekFirst();
	}
}
