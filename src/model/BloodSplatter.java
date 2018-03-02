package model;

import java.util.Random;

import gfx.Bitmap;
import gfx.Brush;

class BloodParticle {
	public float x;
	public float y;
	public int size;
	public float speedx;
	public float speedy;
	public float dampen;
	public int color;
	public int trailIdx;
	public int trailCount;
	public int trailDist;
}

public class BloodSplatter {
	private static Random rnd = new Random();
	private static final int MAX_SIZE = 8;
	private static final int MIN_SIZE = 1;
	private static final int MAX_SPEED = 8;
	private static final int MAX_TRAIL = 5;
	private static final int TRAIL_DIST = 5;
	
	private int x;
	private int y;
	private BloodParticle[] particles;
	private BloodParticle[] trail;
	
	public BloodSplatter(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.particles = new BloodParticle[cnt];
		
		int trailCnt = 0;
		for (int i = 0; i < cnt; i++) {
			BloodParticle part = new BloodParticle();
			this.particles[i] = part;
			
			int siz = rnd.nextInt((MAX_SIZE - MIN_SIZE) / 2) + MIN_SIZE;
			if (rnd.nextBoolean() && rnd.nextBoolean() && rnd.nextBoolean()) {
				siz = rnd.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
			}
			int speed = MAX_SPEED - siz;
			
			float dir = rnd.nextFloat() * 3.141592f;
			float speedx = (float) (Math.cos(dir) * speed);
			float speedy = (float) (Math.sin(dir) * speed);
			
			int startx = x + (int)(speedx * rnd.nextFloat() * 2.0f);
			int starty = y + (int)(speedy * rnd.nextFloat() * 2.0f);
			
			int col = 0x7A + rnd.nextInt(0xEC - 0x7A);
			
			part.x = startx;
			part.y = starty;
			
			part.speedx = speedx;
			part.speedy = speedy;
			
			part.size = siz;
			part.color = 0xff000000 | (col << 16);
			
			part.dampen = rnd.nextFloat() / 2.0f + 0.3f;
			boolean trails = part.size <= MAX_SIZE / 2;
			if (trails) {
				part.trailIdx = trailCnt;
				trailCnt++;
			}
			else {
				part.trailIdx = -1;
			}
		}
		
		this.trail = new BloodParticle[trailCnt * MAX_TRAIL];
		for (int i = 0; i < this.trail.length; i++) {
			this.trail[i] = new BloodParticle();
		}
	}
	
	public void update() {
		for (BloodParticle part : this.particles) {
			part.x += part.speedx;
			part.y += part.speedy;
			
			part.speedx *= part.dampen;
			part.speedy *= part.dampen;
			
			if (part.trailIdx != -1) {
				int baseIdx = part.trailIdx * MAX_TRAIL + part.trailCount;
				part.trailDist++;
				if (part.trailDist % TRAIL_DIST == 0 && part.trailCount < MAX_TRAIL) {
					part.trailCount++;
					BloodParticle t = this.trail[baseIdx];
					t.size = part.size;
					t.x = part.x;
					t.y = part.y;
					t.color = part.color;
				}
			}
		}
	}
	
	public void render(Bitmap bmp) {
		Brush b = bmp.getBrush();
		bmp.setBrush(Brush.SIMPLE_BRUSH);
		
		for (BloodParticle part : this.particles) {
			bmp.blitRect((int)part.x, (int)part.y, part.size, part.size, part.color);
			
			if (part.trailIdx != -1) {
				int baseIdx = part.trailIdx * MAX_TRAIL;
				for (int i = 0; i < part.trailCount; i++) {
					BloodParticle t = this.trail[baseIdx + i];
					bmp.blitRect((int)t.x, (int)t.y, t.size, t.size, t.color);
				}
			}
		}
		
		bmp.setBrush(b);
	}
}
