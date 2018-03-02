package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Crate;
import entities.Entity;
import entities.Worker;
import fields.Field;
import fields.Floor;
import fields.Start;
import fields.Target;
import fields.Wall;
import gfx.Bitmap;
import gfx.Brush;
import io.Keyboard;
import main.Game;

public class Grid {
	private static final PlayerControls[] CONTROLS = new PlayerControls[] {
		new PlayerControls(Keyboard.UP, Keyboard.DOWN, Keyboard.LEFT, Keyboard.RIGHT),
		new PlayerControls(Keyboard.W, Keyboard.S, Keyboard.A, Keyboard.D),
		new PlayerControls(Keyboard.U, Keyboard.J, Keyboard.H, Keyboard.K),
		new PlayerControls(Keyboard.NP_8, Keyboard.NP_5, Keyboard.NP_4, Keyboard.NP_6),
	};
	
	private static final Brush[] BRUSHES = new Brush[] {
		Brush.IGNORE_BRUSH,
		new Brush.RecolorBrush(new int[] { 0xff661B1B, 0xff51322B, 0xffBA3535, 0xff7C0000, 0xffFF3838, 0xffFF7F59, 0xffA83D2F, 0xffBA2121 }),
		new Brush.RecolorBrush(new int[] { 0xff1B661B, 0xff2B512B, 0xff40BA35, 0xff0A7C00, 0xff38FF38, 0xff59FF59, 0xff2FA82F, 0xff2EBA21 }),
		new Brush.RecolorBrush(new int[] { 0xff66581B, 0xff514D2B, 0xffBABA35, 0xff7C7400, 0xffFFE738, 0xffFFF359, 0xffA8A02F, 0xffBAAF21 }),
	};
	
	private int width;
	private int height;
	private Field[] fields;
	private List<Entity> entities;
	private List<IRenderable> renderables;
	private int xOff;
	private int yOff;
	private ShadowLayer shadowScreen;
	
	public static Grid fromFile(String path, int xoff, int yoff) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
			String lvl = "";
			String line;
			while ((line = reader.readLine()) != null) {
				lvl += line + ' ';
			}
			String[] lvlData = lvl.split(";");
			int w = Integer.parseInt(lvlData[0].trim());
			int h = Integer.parseInt(lvlData[1].trim());
			
			String[] fieldsStr = lvlData[2].trim().split(" ");
			// XXX: assert that fieldsStr.length == w * h
			
			Grid g = new Grid(w, h, xoff, yoff);
			
			int pcnt = 0;
			// XXX: Hardcoded
			Field[] fields = new Field[w * h];
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					switch (Integer.parseInt(fieldsStr[x + y * w])) {
					case 0:
						fields[x + y * w] = new Floor(g, x * Game.TILE_WIDTH, y * Game.TILE_HEIGHT);
						break;
						
					case 1:
						fields[x + y * w] = new Wall(g, x * Game.TILE_WIDTH, y * Game.TILE_HEIGHT);
						break;
					
					case 2:
						fields[x + y * w] = new Target(g, x * Game.TILE_WIDTH, y * Game.TILE_HEIGHT);
						break;
						
					case 3:
						Start f = new Start(g, x * Game.TILE_WIDTH, y * Game.TILE_HEIGHT);
						fields[x + y * w] = f;
						Worker e = new Worker(g, fields[x + y * w], Direction.Down, CONTROLS[pcnt], f, BRUSHES[pcnt]);
						++pcnt;
						g.addEntity(e);
						f.setWorker(e);
						break;
						
					default:
						// XXX: Error?
					}
				}
			}
			g.setFields(fields);
			
			int entCnt = Integer.parseInt(lvlData[3].trim());
			// XXX: assert that lvlData.length - 4 == entCnt
			// XXX: Hardcoded
			for (int i = 0; i < entCnt; i++) {
				String[] entData = lvlData[4 + i].trim().split(",");
				Entity e = null;
				switch (Integer.parseInt(entData[0])) {
				case 0:
					e = new Crate(g, g.getField(Integer.parseInt(entData[1]), Integer.parseInt(entData[2])));
					break;
				
				default:
					// XXX: Error?
				}
				g.addEntity(e);
			}
			
			reader.close();
			return g;
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private Grid(int w, int h, int xoff, int yoff) {	
		this.width = w;
		this.height = h;
		
		this.entities = new ArrayList<Entity>();
		this.renderables = new ArrayList<IRenderable>();
		
		this.xOff = xoff;
		this.yOff = yoff;
		
		this.shadowScreen = new ShadowLayer(w * Game.TILE_WIDTH, h * Game.TILE_HEIGHT, Brush.IGNORE_BRUSH);
		renderables.add(this.shadowScreen);
	}
	
	private void setFields(Field[] fields) {
		this.fields = fields;
		for (Field f : fields) {
			renderables.add(f);
		}
		setUpNeighbors();
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
	
	@SuppressWarnings("unused")
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
