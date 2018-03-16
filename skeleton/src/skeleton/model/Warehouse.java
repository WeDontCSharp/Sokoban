package skeleton.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
	private List<Entity> entities;
	
	public Warehouse(int w, int h) {
		this.entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
}
