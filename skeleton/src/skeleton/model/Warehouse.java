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

import skeleton.view.IView;
import skeleton.view.message.ControlMessage;
import skeleton.view.message.GameOverStateChangeMessage;
import skeleton.view.message.HealthStateChangeMessage;
import skeleton.view.message.PlaceControlMessage;
import skeleton.view.message.StateChangeMessage;
import skeleton.view.message.StateChangeMessageType;
import skeleton.view.message.StepControlMessage;

/**
 * A class representing a container for the fields and entities, also taking
 * part as a level in the game.
 */
public class Warehouse implements Serializable {
	
	/**
	 * Represents a game-ending type.
	 */
	public enum EndType {
		Nothing, Target, Crate, Player;
		
		/**
		 * Converts an end type to a string.
		 * @param et The end type.
		 * @return The string value of the end type, '?' if not recognised.
		 */
		public static String toString(EndType et) {
			if (et == null) {
				return "?";
			}
			switch (et) {
			case Nothing: 	return "N";
			case Target: 	return "TR";
			case Crate: 	return "CR";
			case Player: 	return "P";
			default: 		return "?";
			}
		}
	}
	
	/**
	 * The view of the warehouse.
	 */
	private transient IView<StateChangeMessage> graphicsView;
	
	public void sendMessage(StateChangeMessage msg) {
		// XXX: Not required
	}
	
	/**
	 * The warehouse receives a message and forwards it to the view.
	 * @param msg The message.
	 */
	public void receiveMessage(StateChangeMessage msg) {
		if (this.graphicsView == null) {
			return;
		}
		this.graphicsView.receiveMessage(msg);
	}
	
	public void sendMessage(ControlMessage msg) {
		// XXX: Not required
	}
	
	/**
	 * The warehouse receives a message and acts according to it.
	 * @param msg The message.
	 */
	public void receiveMessage(ControlMessage msg) {
		if (this.end != EndType.Nothing) {
			return;
		}
		
		switch (msg.type) {
		case Step: {
			StepControlMessage scm = (StepControlMessage)msg;
			Worker w = this.workers[scm.playerIndex];
			if (w.getHealth() > 0) {
				w.move(scm.direction);
			}
		} break;
		
		case Place: {
			PlaceControlMessage pc = (PlaceControlMessage)msg;
			this.workers[pc.playerIndex].placeItem();
		} break;
		}
	}
	
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
	private int targets;
	private int ontarget;
	

	/**
	 * Blocking status of fields currently
	 */
	private boolean[] blockedMap;
	private EndType end = EndType.Nothing;

	private Worker[] workers;
	private int aliveWorker;
	/**
	 * Creates a warehouse (as a level).
	 * 
	 * @param w The width of the warehouse.
	 * @param h The height of the warehouse.
	 */
	public Warehouse(int w, int h, IView<StateChangeMessage> graphicsView) {
		this.graphicsView = graphicsView;
		
		this.width = w;
		this.height = h;

		this.entities = new ArrayList<Entity>();
		this.fields = new Field[w * h];
		this.targets = 0;
		this.ontarget = 0;
		
		this.blockedMap = new boolean[w * h];
		
		this.workers = new Worker[4];
		this.aliveWorker = 4;
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
	
	public ArrayList<Crate> getCrates(){
		ArrayList<Crate> crates = new ArrayList<Crate>();
		for (Entity e : entities){
			if (!(e instanceof Worker))
				crates.add((Crate)e);
			}
		return crates;
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
		this.blockedMap[x + y * width] = f.isBlocking();
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
	
	public boolean getblockedChecked(int x, int y) {
		if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
			return true;
		}
		return this.blockedMap[x + y * this.width];
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
	
	/**
	 * @return The worker with the highest score.
	 */
	private Worker getHighestScoreWorker() {
		Worker best = null;
		int bestScore = 0;
		for (Worker w : this.workers) {
			if (w == null) {
				continue;
			}
			if (w.getPoints() > bestScore) {
				best = w;
				bestScore = w.getPoints();
			}
			else if (w.getPoints() == bestScore) {
				best = null;
			}
		}
		return best;
	}
	
	/**
	 * Updates everything in the warehouse.
	 */
	public void update() {
		if (this.end != EndType.Nothing) {			
			Worker best = getHighestScoreWorker();
			int pidx = best == null ? -1 : best.getPlayerIndex();
			this.receiveMessage(new GameOverStateChangeMessage(this.end, pidx));
		}
		
		if (this.aliveWorker <= 1) {
			this.end = EndType.Player;
		}
		if (this.ontarget >=  this.targets && this.targets > 0) {
			this.end = EndType.Target;
		}
		
		for (Entity e : this.entities) {
			e.update();
		}
	}
	
	private void addTarget() {
		this.targets++;
	}

	public void addOntarget() {
		this.ontarget++;
	}
	public void removeOntarget() {
		this.ontarget--;
	}
	public void removeAliveWorker() {
		this.aliveWorker--;
	}
	public boolean getBlocking(Field f) {
		int pos = -1;
		for(int i=0;i<width*height; ++i) {
			if(f == this.fields[i]) {
				pos = i;
				break;
			}
		}
		
		return this.blockedMap[pos];
	}
	
	/**
	 * Updates the blocking map, so that it is noted down if a crate got blocked.
	 */
	public void updateBlocking(Field f, boolean initial) {
		if(f == null) return;
		int pos = -1;
		for(int i=0;i<width*height; ++i) {
			if(f == this.fields[i]) {
				pos = i;
				break;
			}
		}
		
		int x = pos%this.width;
		int y = pos/this.width;
		
		boolean old = this.blockedMap[pos];
		
		Entity e = (f.getEntity());
		boolean containCrate = false;
		if(e != null) {
			containCrate = this.entities.contains(e);
			for(int i = 0; i <4; ++i) {
				if(e == this.workers[i]) containCrate = false;
			}
		}
		
		this.blockedMap[pos] = f.isBlocking() || containCrate 
				&&((this.getblockedChecked(x-1, y)||this.getblockedChecked(x+1, y))
				&&(this.getblockedChecked(x, y-1)||this.getblockedChecked(x, y+1)));
		
		 if(this.blockedMap[pos] != old) {
			 updateBlocking(this.getFieldChecked(x-1, y), false);
			 updateBlocking(this.getFieldChecked(x, y-1), false);
			 updateBlocking(this.getFieldChecked(x+1, y), false);
			 updateBlocking(this.getFieldChecked(x, y+1), false);
		 }
		 
		 if(initial) {
			 ArrayList<Entity> crates = new ArrayList<Entity>();
			 crates.addAll(this.entities);
			 for(int i = 0; i < 4; ++i) {
			 		crates.remove(this.workers[i]);
			 }
			 boolean allBlocked = true;
			 for (Entity crate : crates) {
				 if(!((Crate)crate).isStuck()) {
					 allBlocked = false;
				 }
			 }
			 if(allBlocked) {
				 this.end = EndType.Crate;
			 }
		 }
	}
	
	public EndType getEnd() {
		return end;
	}
	
	private void setWorker(int i, Entity e) {
		this.workers[i] = (Worker)e;
	}
	
	public Worker getWorker(int i) {
		return this.workers[i];
	}

	/**
	 * Loads a warehouse (a map) from a file.
	 * @param path
	 * @param gw
	 * @return The ready warehouse.
	 * @throws LevelFormatException
	 * @throws FileNotFoundException
	 */
	public static Warehouse fromFile(String path, IView<StateChangeMessage> gw) throws LevelFormatException, FileNotFoundException {
		JsonReader reader = Json.createReader(new BufferedInputStream(new FileInputStream(path)));
		JsonObject mapObj = reader.readObject();

		int width = mapObj.getInt("width");
		int height = mapObj.getInt("height");
		int tileSize = mapObj.getInt("tilewidth");

		Warehouse wh = new Warehouse(width, height, gw);

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
					int field_x = index % width;
					int field_y = index / width;

					switch (fieldId) {
					case 0:
						break;
					case 1:
						field = new WallWrapper(wh, field_x, field_y);
						break;
					case 2:
						field = new FloorWrapper(wh, field_x, field_y);
						break;
					case 3:
						field = new TargetWrapper(wh, field_x, field_y);
						wh.addTarget();
						break;
					case 4:
						field = new SwitchWrapper(wh, field_x, field_y);
						break;
					case 5:
						field = new HoleWrapper(wh, field_x, field_y);
						break;
					case 6:
						// Spawn for P1
						field = new SpawnWrapper(wh, field_x, field_y);
						spawns[0] = (Spawn) field;
						break;
					case 7:
						// Spawn for P2
						field = new SpawnWrapper(wh, field_x, field_y);
						spawns[1] = (Spawn) field;
						break;
					case 8:
						// Spawn for P3
						field = new SpawnWrapper(wh, field_x, field_y);
						spawns[2] = (Spawn) field;
						break;
					case 9:
						// Spawn for P4
						field = new SpawnWrapper(wh, field_x, field_y);
						spawns[3] = (Spawn) field;
						break;
					// XXX
					default:
						throw new LevelFormatException();
					}

					if (field != null) {
						wh.setField(field, field_x, field_y);
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
						ent = new CrateWrapper(wh, wh.getField(index % width, index / width));
						if(wh.getField(index % width, index / width) instanceof Target) wh.addOntarget();
						break;
					case 11:
						ent = new WorkerWrapper(wh, wh.getField(index % width, index / width), Direction.Down, 0);
						owners[0] = (Worker) ent;
						wh.setWorker(0, ent);
						break;
					case 12:
						ent = new WorkerWrapper(wh, wh.getField(index % width, index / width), Direction.Down, 1);
						owners[1] = (Worker) ent;
						wh.setWorker(1, ent);
						break;
					case 13:
						ent = new WorkerWrapper(wh, wh.getField(index % width, index / width), Direction.Down, 2);
						owners[2] = (Worker) ent;
						wh.setWorker(2, ent);
						break;
					case 14:
						ent = new WorkerWrapper(wh, wh.getField(index % width, index / width), Direction.Down, 3);
						owners[3] = (Worker) ent;
						wh.setWorker(3, ent);
						break;
					case 15:
						ent = new LifeCrateWrapper(wh, wh.getField(index % width, index / width));
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
		
		for (int i = 0; i < 4; ++i) {
			if (spawns[i] != null) {		
				spawns[i].setOwner(owners[i]);
			}
		}
		
		for(int i = 0; i < 4; ++i) {
			if(owners[i] == null) wh.removeAliveWorker();
		}
		
		for(int i = 0; i< wh.width*wh.height; ++i) {
			wh.updateBlocking(wh.fields[i], true);
		}
		
		wh.setUpNeighbors();
		return wh;
	}
}
