package skeleton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a container for the fields and entities, also taking part as a level in the game.
 */
public class Warehouse {
	/**
	 * The entities that are currently inside the warehouse.
	 */
	private List<Entity> entities;
	/**
	 * The fields that are currently inside the warehouse.
	 */
	private List<Field> fields;
	
	/**
	 * Creates a warehouse (as a level).
	 * @param w The width of the warehouse.
	 * @param h The height of the warehouse.
	 */
	public Warehouse(int w, int h) {
		this.entities = new ArrayList<Entity>();
	}
	
	/**
	 * Adds an entity to the warehouse.
	 * @param e The entity.
	 * @param x The entity's x coordinate.
	 * @param y The entity's y coordinate.
	 */
	public void addEntity(Entity e, int x, int y) {
		entities.add(e);
	}
	
	/**
	 * Adds a field to the warehouse.
	 * @param f The field.
	 * @param x The field's x coordinate.
	 * @param y The field's y coordinate.
	 */
	public void setField(Field f, int x, int y) {
		// XXX: Stub
	}
	
	/**
	 * Initializes the warehouse, setting up the entities and the fields.
	 */
	public void initialize() {
		// XXX: Stub
	}
}
