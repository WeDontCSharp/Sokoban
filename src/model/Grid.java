package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gfx.Screen;
import main.Game;

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
		
		Hole h1 = null;
		Hole h2 = null;
		for (int j = 0; j < h; j++) {
			for (int i = 0; i < w; i++) {
				if (j == 0 || j == h - 1 || i == 0 || i == w - 1) {
					fields.add(new Wall(i * Game.TILE_WIDTH, j * Game.TILE_HEIGHT));
				}
				else {
					if (j == 5 && i == 5) {
						fields.add(new Target(i * Game.TILE_WIDTH, j * Game.TILE_HEIGHT));
					}else if (j == 7 && i == 7) {
						h1 = new Hole(i * Game.TILE_WIDTH, j * Game.TILE_HEIGHT);
						fields.add(h1);
					}else if (j == 4 && i == 4) {
						h2 = new Hole(i * Game.TILE_WIDTH, j * Game.TILE_HEIGHT);
						fields.add(h2);
					}else if (j == 9 && i == 10) {
						Switch s1 = new Switch(i * Game.TILE_WIDTH, j * Game.TILE_HEIGHT);
						s1.holes.add(h1);
						s1.holes.add(h2);
						fields.add(s1);
					}else {
						fields.add(new Floor(i * Game.TILE_WIDTH, j * Game.TILE_HEIGHT));
					}
				}
			}
		}
		
		entities.add(new Worker(this, fields.get(70), Direction.Right, false));
		entities.add(new Worker(this, fields.get(69), Direction.Right, true));
		entities.add(new Worker(this, fields.get(68), Direction.Right, true));
		entities.add(new Crate(this, fields.get(67)));
		entities.add(new Crate(this, fields.get(66)));
		entities.add(new LifeCrate(this, fields.get(62)));
		
	}
	
	public void update() {
		for (Field f : fields) {
			f.update();
		}
		for (Entity e : entities) {
			e.update();
		}
		Collections.sort(entities, (Entity e1, Entity e2) -> e1.getY() - e2.getY());
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
		int x = xp / Game.TILE_WIDTH;
		int y = yp / Game.TILE_HEIGHT;
		return fields.get(x + y * width);
	}
}
