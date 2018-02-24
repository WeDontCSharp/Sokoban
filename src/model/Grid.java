package model;

import java.util.ArrayList;
import java.util.List;

import gfx.Screen;

public class Grid {
	private int width;
	private int height;
	private List<Field> fields;
	private List<Entity> entities;
	
	public Grid(int w, int h) {
		this.width = w;
		this.height = h;
		
		this.fields = new ArrayList<Field>();
		this.entities = new ArrayList<Entity>();
		
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				if (j == 0 || j == h - 1 || i == 0 || i == w - 1) {
					fields.add(new Wall(i * 16, j * 16));
				}
				else {
					if (j == 5 && i == 5) {
						fields.add(new Target(i * 16, j * 16));
					}else {
						fields.add(new Floor(i * 16, j * 16));
					}
				}
			}
		}
		
		entities.add(new Worker(this, fields.get(44), Direction.Right, false));
		entities.add(new Worker(this, fields.get(49), Direction.Right, true));
		entities.add(new Worker(this, fields.get(50), Direction.Right, true));
		entities.add(new Crate(this, fields.get(47)));
		entities.add(new Crate(this, fields.get(48)));
	}
	
	public void update() {
		for (Field f : fields) {
			f.update();
		}
		for (Entity e : entities) {
			e.update();
		}
	}
	
	public void render(Screen screen) {
		for (Field f : fields) {
			f.render(screen);
		}
		for (Entity e : entities) {
			e.render(screen);
		}
	}
	
	public Field getFieldPix(int xp, int yp) {
		int x = xp / 16;
		int y = yp / 16;
		return fields.get(x + y * width);
	}
}
