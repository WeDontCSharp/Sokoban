package skeleton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a container for the fields and entities, also taking part as a level in the game.
 */
public class Warehouse {
	/**
	 * The dimensions of the warehouse in field units.
	 */
	private int width, height;
	/**
	 * The entities that are currently inside the warehouse.
	 */
	private List<Entity> entities;
	/**
	 * The fields that are currently inside the warehouse.
	 */
	private Field[] fields;
	
	/**
	 * Creates a warehouse (as a level).
	 * @param w The width of the warehouse.
	 * @param h The height of the warehouse.
	 */
	public Warehouse(int w, int h) {
		this.width = w;
		this.height = h;
		
		this.entities = new ArrayList<Entity>();
		this.fields = new Field[w * h];
		
		initialize();
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
		this.fields[x + y * width] = f;
	}
	
	/**
	 * Gets a field from the level. No bound check performed.
	 * 
	 * @param x The field's y coordinate.
	 * @param y The field's y coordinate.
	 * @return The field at the given coords.
	 */
	public Field getField(int x, int y) {
		return this.fields[x + y * this.width];
	}
	
	/**
	 * Gets a field from the level performing a bound check before.
	 * 
	 * @param x The field's x coordinate.
	 * @param y The field's y coordinate.
	 * @return The field if it's in bounds, null otherwise.
	 */
	public Field getFieldChecked(int x, int y) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
			return null;
		}
		return this.getField(x, y);
	}
	
	/**
	 * Initializes the warehouse, setting up the entities and the fields.
	 */
	public void initialize() {
		for (int y = 0; y < this.height; ++y) {
			for (int x = 0; x < this.width; ++x) {
				Field f = null;
				if (x == 0 || y == 0 || x == this.width - 1 || y == this.height - 1) {
					f = new Wall(this);
				}
				else {
					f = new Floor(this);
				}
				this.setField(f, x, y);
			}
		}
		setUpNeighbors();
	}
	
	/**
	 * Sets up neighborhood relationships between fields. 
	 */
	private void setUpNeighbors() {
		for (int y = 0; y < this.height; ++y) {
			for (int x = 0; x < this.width; ++x) {
				Field f = this.getField(x, y);
				f.setNeighbourField(Direction.Left, this.getFieldChecked(x - 1, y));
				f.setNeighbourField(Direction.Right, this.getFieldChecked(x + 1, y));
				f.setNeighbourField(Direction.Up, this.getFieldChecked(x, y - 1));
				f.setNeighbourField(Direction.Down, this.getFieldChecked(x, y + 1));
			}
		}
	}
}
