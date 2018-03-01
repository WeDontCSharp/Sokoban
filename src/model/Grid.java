package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gfx.Bitmap;
import gfx.Brush;
import main.Game;

public class Grid {
	private int width;
	private int height;
	private Field[] fields;
	private List<Entity> entities;
	private List<IRenderable> renderables;
	private int xOff;
	private int yOff;
	private ShadowLayer shadowScreen;
	
	public Grid(int w, int h, int xoff, int yoff) {	
		this.width = w;
		this.height = h;
		
		this.fields = new Field[w * h];
		this.entities = new ArrayList<Entity>();
		this.renderables = new ArrayList<IRenderable>();
		
		this.xOff = xoff;
		this.yOff = yoff;
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				Field f = null;
				if (x == 0 || y == 0 || x == w - 1 || y == h - 1) {
					f = new Wall(this, x * Game.TILE_WIDTH, y * Game.TILE_HEIGHT);
				}
				else {
					f = new Floor(this, x * Game.TILE_WIDTH, y * Game.TILE_HEIGHT);
				}
				
				setField(x, y, f);
			}
		}
		
		setField(5, 5, new Wall(this, 5 * Game.TILE_WIDTH, 5 * Game.TILE_HEIGHT));
		
		setUpNeighbors();

		addEntity(new Worker(this, getField(3, 3), Direction.Right, false));
		addEntity(new Crate(this, getField(5, 3)));
		addEntity(new Crate(this, getField(6, 3)));
		addEntity(new Worker(this, getField(7, 3), Direction.Right, true));
		
		this.shadowScreen = new ShadowLayer(w * Game.TILE_WIDTH, h * Game.TILE_HEIGHT, Brush.IGNORE_BRUSH);
		renderables.add(this.shadowScreen);
		this.shadowScreen.getBitmap().clear(0xffff00ff);
		//renderables.add(this.shadowScreen);
	}
	
	private void setUpNeighbors() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Field cur = fields[x + y * width];
				if (x > 0) {
					cur.setNeighbor(Direction.Left, getField(x - 1, y));
				}
				if (x < width - 1) {
					cur.setNeighbor(Direction.Right, getField(x + 1, y));
				}
				if (y > 0) {
					cur.setNeighbor(Direction.Up, getField(x, y - 1));
				}
				if (y < height - 1) {
					cur.setNeighbor(Direction.Down, getField(x, y + 1));
				}
			}
		}
	}
	
	public void update() {
		for (Field f : fields) {
			f.update();
		}
		for (Entity e : entities) {
			e.update();
		}
		
		Collections.sort(renderables, (IRenderable r1, IRenderable r2) -> (r2.getDepth() - r1.getDepth()));
	}
	
	public void render(Bitmap bmp) {
		this.shadowScreen.getBitmap().clear(0xffff00ff);
		for (IRenderable r : renderables) {
			r.renderShadow(bmp);
		}
		for (IRenderable r : renderables) {
			//if ( r.getDepth() < 5)
			r.render(bmp, xOff, yOff);
		}
		//bmp.blit(0, 0, 0, 0, bmp.getWidth(), bmp.getHeight(), this.shadowScreen.getBitmap());
	}
	
	public Field getField(int xp, int yp) {
		return fields[xp + yp * width];
	}
	
	private void setField(int xp, int yp, Field f) {
		fields[xp + yp * width] = f;
		renderables.add(f);
	}
	
	private void addEntity(Entity e) {
		entities.add(e);
		renderables.add(e);
	}
	
	public Bitmap getShadowScreen() {
		return shadowScreen.getBitmap();
	}
}
