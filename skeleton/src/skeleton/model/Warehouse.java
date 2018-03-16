package skeleton.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
	private List<Entity> entities;
	
	public Warehouse(int w, int h) {
		this.entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e, int x, int y) {
		entities.add(e);
	}
	
	public void setField(Field f, int x, int y) {
		// XXX: Stub
	}
	
	public void initialize() {
		// XXX: Stub
	}
}
