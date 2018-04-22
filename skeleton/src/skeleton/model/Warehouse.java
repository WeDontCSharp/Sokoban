package skeleton.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 * A class representing a container for the fields and entities, also taking
 * part as a level in the game.
 */
public class Warehouse implements Serializable {
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

	private Worker[] workers;

	/**
	 * Creates a warehouse (as a level).
	 * 
	 * @param w The width of the warehouse.
	 * @param h The height of the warehouse.
	 */
	public Warehouse(int w, int h) {
		this.width = w;
		this.height = h;

		this.entities = new ArrayList<Entity>();
		this.fields = new Field[w * h];
		
		this.workers = new Worker[4];
	}

	/**
	 * Gets the width of the warehouse.
	 *
	 * @return The width of the warehouse.
	 */
	public int getWidth(){
		return this.width;
	}

	/**
	 * Gets the height of the warehouse.
	 *
	 * @return The height of the warehouse.
	 */
	public int getHeight(){
		return this.height;
	}

	/**
	 * Adds an entity to the warehouse.
	 * 
	 * @param e The entity.
	 * @param x The entity's x coordinate.
	 * @param y The entity's y coordinate. XXX: Why coordinates???
	 */
	public void addEntity(Entity e, int x, int y) {
		entities.add(e);
	}

	/**
	 * Adds a field to the warehouse.
	 * 
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
	
	public void update() {
		for (Entity e : this.entities) {
			e.update();
		}
	}
	
	private void setWorker(int i, Entity e) {
		this.workers[i] = (Worker)e;
	}
	
	public Worker getWorker(int i) {
		return this.workers[i];
	}

	public static Warehouse fromFile(String path) throws LevelFormatException, FileNotFoundException {
		JsonReader reader = Json.createReader(new BufferedInputStream(new FileInputStream(path)));
		JsonObject mapObj = reader.readObject();

		int width = mapObj.getInt("width");
		int height = mapObj.getInt("height");
		int tileSize = mapObj.getInt("tilewidth");

		Warehouse wh = new Warehouse(width, height);

		JsonArray layers = mapObj.getJsonArray("layers");

		// Matching spawns with their owners
		Spawn[] spawns = new Spawn[4];
		Worker[] owners = new Worker[4];
					
		for (JsonValue layer : layers) {
			JsonReader layerReader = Json.createReader(new StringReader(layer.toString()));
			JsonObject layerObj = layerReader.readObject();

			String layerName = layerObj.getString("name").toLowerCase();
			
			if (layerName.equals("fields")) {
				JsonArray fields = layerObj.getJsonArray("data");
				int index = 0;
				for (JsonValue fieldIdStr : fields) {
					int fieldId = Integer.parseInt(fieldIdStr.toString());
					Field field = null;

					switch (fieldId) {
					case 0:
						break;
					case 1:
						field = new Wall(wh);
						break;
					case 2:
						field = new Floor(wh);
						break;
					case 3:
						field = new Target(wh);
						break;
					case 4:
						field = new Switch(wh);
						break;
					case 5:
						field = new Hole(wh);
						break;
					case 6:
						// Spawn for P1
						field = new Spawn(wh);
						spawns[0] = (Spawn) field;
						break;
					case 7:
						// Spawn for P2
						field = new Spawn(wh);
						spawns[1] = (Spawn) field;
						break;
					case 8:
						// Spawn for P3
						field = new Spawn(wh);
						spawns[2] = (Spawn) field;
						break;
					case 9:
						// Spawn for P4
						field = new Spawn(wh);
						spawns[3] = (Spawn) field;
						break;
					// XXX
					default:
						throw new LevelFormatException();
					}

					if (field != null) {
						wh.setField(field, index % width, index / width);
					}
					index++;
				}
			} else if (layerName.equals("entities")) {
				JsonArray entities = layerObj.getJsonArray("data");
				int index = 0;

				for (JsonValue entityIdStr : entities) {
					int entityId = Integer.parseInt(entityIdStr.toString());
					Entity ent = null;
					switch (entityId) {
					case 0:
						break;
					case 10:
						ent = new Crate(wh, wh.getField(index % width, index / width));
						break;
					case 11:
						ent = new Worker(wh, wh.getField(index % width, index / width), Direction.Down);
						owners[0] = (Worker) ent;
						wh.setWorker(0, ent);
						break;
					case 12:
						ent = new Worker(wh, wh.getField(index % width, index / width), Direction.Down);
						owners[1] = (Worker) ent;
						wh.setWorker(1, ent);
						break;
					case 13:
						ent = new Worker(wh, wh.getField(index % width, index / width), Direction.Down);
						owners[2] = (Worker) ent;
						wh.setWorker(2, ent);
						break;
					case 14:
						ent = new Worker(wh, wh.getField(index % width, index / width), Direction.Down);
						owners[3] = (Worker) ent;
						wh.setWorker(3, ent);
						break;
					case 15:
						ent = new LifeCrate(wh, wh.getField(index % width, index / width));
						break;
					// XXX
					default:
						throw new LevelFormatException();
					}

					if (ent != null) {
						wh.addEntity(ent, index % width, index / width);
					}
					index++;
				}
			} else if (layerName.equals("connections")) {
				JsonArray connections = layerObj.getJsonArray("objects");
				for (JsonValue obj : connections) {
					JsonObject oobj = (JsonObject) obj;
					int baseX = oobj.getInt("x");
					int baseY = oobj.getInt("y");
					JsonArray points = oobj.getJsonArray("polyline");
					if (points.size() != 2) {
						throw new LevelFormatException();
					}

					JsonObject p1 = (JsonObject) points.get(0);
					JsonObject p2 = (JsonObject) points.get(1);

					int x0 = p1.getInt("x") + baseX;
					int y0 = p1.getInt("y") + baseY;
					int x1 = p2.getInt("x") + baseX;
					int y1 = p2.getInt("y") + baseY;

					Field f1 = wh.getField(x0 / tileSize, y0 / tileSize);
					Field f2 = wh.getField(x1 / tileSize, y1 / tileSize);

					if (f1 instanceof Switch) {
						if (f2 instanceof Hole) {
							((Switch) f1).addHole((Hole) f2);
						} else {
							throw new LevelFormatException();
						}
					} else if (f2 instanceof Switch) {
						if (f1 instanceof Hole) {
							((Switch) f2).addHole((Hole) f1);
						} else {
							throw new LevelFormatException();
						}
					} else {
						throw new LevelFormatException();
					}
				}
			} else {
				// XXX
				throw new LevelFormatException();
			}
			
		}
		
		for (int i = 0; i < 3; ++i) {
			if (spawns[i] != null) {			
				spawns[i].setOwner(owners[i]);
			}
		}
		
		wh.setUpNeighbors();
		return wh;
	}
}
