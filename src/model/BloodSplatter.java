package model;

import java.util.Random;

import gfx.Bitmap;
import gfx.Brush;
import main.Game;

class BloodParticle {
	public int x;
	public int y;
	public int size;
	public int color;
}

class BloodTrail {
	public float x;
	public float y;
	public float xspeed;
	public float yspeed;
	public float dampen;
	public float size;
	public float sizeDamp;
	public BloodParticle[] particles;
	public int particleCnt;
	public int tickCnt;
	public int tickPerPart;
	public int color;
	public boolean active;
	
	public BloodTrail(float x, float y, float xs, float ys, float damp, float siz, float sdamp, int maxParts, int tpp, int col) {
		this.x = x;
		this.y = y;
		this.xspeed = xs;
		this.yspeed = ys;
		this.dampen = damp;
		this.size = siz;
		this.sizeDamp = sdamp;
		this.particles = new BloodParticle[maxParts];
		for (int i = 0; i < maxParts; i++) {
			this.particles[i] = new BloodParticle();
		}
		this.particleCnt = 0;
		this.tickCnt = 0;
		this.tickPerPart = tpp;
		this.color = col;
		this.active = true;
	}
}

public class BloodSplatter {
	private static Random rnd = new Random();
	
	private static float MIN_SPEED = 8.0f;
	private static float MAX_SPEED = 12.0f;
	private static float MIN_SIZE = 1.0f;
	private static float MAX_SIZE = 8.0f;
	private static int MAX_PARTS = 16;
	private static float SPEED_EPS = 0.01f;
	
	private static final float Y_SCALE = (float)Game.TILE_HEIGHT / (float)Game.TILE_WIDTH;
	
	private int x;
	private int y;
	private float startDir;
	private float endDir;
	private BloodParticle[] baseParts;
	private BloodTrail[] trails;
	private int inactiveCnt;
	
	public BloodSplatter(int x, int y, int cnt, float sd, float ed) {
		this.x = x;
		this.y = y;
		
		this.startDir = sd;
		this.endDir = ed;
		
		this.inactiveCnt = 0;
		
		this.trails = new BloodTrail[cnt];
		for (int i = 0; i < cnt; i++) {
			this.trails[i] = generateTrail();
		}
		
		this.baseParts = new BloodParticle[20];
		for (int i = 0; i < this.baseParts.length; i++) {
			BloodParticle part = new BloodParticle();
			this.baseParts[i] = part;
			
			float dir = randFloat(startDir, endDir);
			float off_x = (float)(Math.cos(dir)) * 6.0f;
			float off_y = (float)(Math.sin(dir)) * 6.0f;
			
			part.x = (int)(x + off_x);
			part.y = (int)(y + off_y);
			
			int rcol = 0x7A + rnd.nextInt(0xEC - 0x7A);
			int col = 0xff000000 | (rcol << 16);
			
			part.color = col;
			
			part.size = (int)randFloat(MAX_SIZE / 2, MAX_SIZE);
		}
	}
	
	private static float randFloat(float min, float max) {
		return rnd.nextFloat() * (max - min) + min;
	}
	
	private BloodTrail generateTrail() {
		// generate a direction and speed
		float dir = randFloat(startDir, endDir);
		float speed = randFloat(MIN_SPEED, MAX_SPEED);
		
		float speed_x = (float)(Math.cos(dir) * speed);
		float speed_y = (float)(Math.sin(dir) * speed) * Y_SCALE;
		
		float siz = randFloat(MIN_SIZE, MAX_SIZE);
		
		float damp = randFloat(0.7f, 0.85f);
		float sdamp = randFloat(0.5f, 1.0f) * 0.8f;
		
		int tpp = rnd.nextInt(2);
		
		int rcol = 0x7A + rnd.nextInt(0xEC - 0x7A);
		int col = 0xff000000 | (rcol << 16);
		
		float offs = rnd.nextFloat();
		return new BloodTrail(x + offs * speed_x, y + offs * speed_y, speed_x, speed_y, damp, siz, sdamp, MAX_PARTS, tpp, col);
	}
	
	public void update() {
		for (BloodTrail trail : this.trails) {
			if (!trail.active) {
				continue;
			}
			
			trail.x += trail.xspeed;
			trail.y += trail.yspeed;
			
			trail.xspeed *= trail.dampen;
			trail.yspeed *= trail.dampen;
			
			if (Math.abs(trail.xspeed) + Math.abs(trail.yspeed) > SPEED_EPS) {
				trail.size *= trail.sizeDamp;
			}
			else {
				trail.active = false;
				this.inactiveCnt++;
			}
			
			trail.tickCnt++;
			if ((trail.tickPerPart == 0 || trail.tickCnt % trail.tickPerPart == 0) && trail.particleCnt < MAX_PARTS) {
				BloodParticle part = trail.particles[trail.particleCnt];
				part.x = (int)trail.x;
				part.y = (int)trail.y;
				
				part.size = (int)trail.size;
				part.color = trail.color;
				
				trail.particleCnt++;
			}
		}
	}
	
	public void render(Bitmap bmp) {
		Brush b = bmp.getBrush();
		bmp.setBrush(Brush.SIMPLE_BRUSH);
		
		for (BloodParticle part : this.baseParts) {
			bmp.blitRect(part.x, part.y, part.size, part.size, part.color);
		}
		
		for (BloodTrail trail : this.trails) {
			bmp.blitRect((int)trail.x, (int)trail.y, (int)trail.size, (int)trail.size, trail.color);
			
			for (int i = 0; i < trail.particleCnt; i++) {
				BloodParticle part = trail.particles[i];
				
				bmp.blitRect(part.x, part.y, part.size, part.size, part.color);
			}
		}
		
		bmp.setBrush(b);
	}
	
	public boolean active() {
		return this.inactiveCnt < this.trails.length;
	}
}
