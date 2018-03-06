package skeleton.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Warehouse {
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 11;
	
	private int width;
	private int height;
	private Field[] fields;
	private List<Entity> entities;
	private int xOff;
	private int yOff;
	
	public Warehouse(int w, int h, int xoff, int yoff) {	
		this.width = w;
		this.height = h;
		
		this.fields = new Field[w * h];
		this.entities = new ArrayList<Entity>();
		
		this.xOff = xoff;
		this.yOff = yoff;
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Field f = null;
				if (x == 0 || y == 0 || x == w - 1 || y == h - 1) {
					f = new Wall(this, x * TILE_WIDTH, y * TILE_HEIGHT);
				}
				else {
					f = new Floor(this, x * TILE_WIDTH, y * TILE_HEIGHT);
				}
				
				setField(x, y, f);
			}
		}
		
		setField(5, 5, new Wall(this, 5 * TILE_WIDTH, 5 * TILE_HEIGHT));
		
		setUpNeighbors();

		addEntity(new Worker(this, getField(3, 3), Direction.Right));
		addEntity(new Crate(this, getField(5, 3)));
		addEntity(new Crate(this, getField(6, 3)));
		addEntity(new Worker(this, getField(7, 3), Direction.Right));
		
	}
	
	private void setUpNeighbors() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Field cur = fields[x + y * width];
				if (x > 0) {
					cur.setNeighbourField(Direction.Left, getField(x - 1, y));
				}
				if (x < width - 1) {
					cur.setNeighbourField(Direction.Right, getField(x + 1, y));
				}
				if (y > 0) {
					cur.setNeighbourField(Direction.Up, getField(x, y - 1));
				}
				if (y < height - 1) {
					cur.setNeighbourField(Direction.Down, getField(x, y + 1));
				}
			}
		}
	}
	
	public Field getField(int xp, int yp) {
		return fields[xp + yp * width];
	}
	
	private void setField(int xp, int yp, Field f) {
		fields[xp + yp * width] = f;
	}
	
	private void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void initialize() {
		
	}
}
